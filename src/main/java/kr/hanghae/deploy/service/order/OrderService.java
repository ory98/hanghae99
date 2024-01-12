package kr.hanghae.deploy.service.order;

import kr.hanghae.deploy.domain.order.OrderedEvent;
import kr.hanghae.deploy.redisson.DistributedLock;
import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.order.OrderRepository;
import kr.hanghae.deploy.domain.order.OrderStatus;
import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.point.Point;
import kr.hanghae.deploy.domain.point.PointRepository;
import kr.hanghae.deploy.domain.point.PointState;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.product.ProductRepository;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import kr.hanghae.deploy.dto.order.service.request.OrderServiceRequest;
import kr.hanghae.deploy.dto.order.service.response.OrderResponse;
import kr.hanghae.deploy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PointRepository pointRepository;
    private final UserService userService;
    private final ApplicationEventPublisher publisher;


    @Transactional
    @DistributedLock(key = "T(java.lang.String).format('Order%s', #username)")
    public OrderResponse saveOrder(String username, List<OrderProductInfo> orderProductInfos) {
        OrderServiceRequest request = new OrderServiceRequest(username, orderProductInfos);

        User user = userService.getUserInfo(request.getUsername());

        List<Product> products = getRepositoryProducts(request);

        //주문 생성
        Order newOrder = Order.builder()
                .products(products)
                .orderNumber(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.INIT)
                .userId(user.getId())
                .build();

        // 주문상품 갯수 추가 및 총 가격 가져오기
        Long totalPrice = updateOrderProductAndGetTotalPrice(request, newOrder);

        // 포인트 체크 및 포인트 차감
        user.decreasePoint(totalPrice);

        // 포인트 history 생성
        Point minusPoint = Point.builder()
                .point(totalPrice)
                .state(PointState.MINUS)
                .user(user)
                .build();

        orderRepository.save(newOrder);
        pointRepository.save(minusPoint);

        List<OrderProduct> orderProductList = newOrder.getOrderProducts();
        log.info(username + " 회원의 주문이 성공하였습니다. 현재 보유 포인트 : " + user.getPoint());

        // 외부 데이터 플랫폼 전송 (실패하더라도 주문 성공)
        publisher.publishEvent(new OrderedEvent(newOrder.getId(), orderProductList, totalPrice));

        return OrderResponse.of(user.getUsername(), orderProductList, totalPrice);
    }


    @Transactional(readOnly = true)
    public List<Product> getRepositoryProducts(OrderServiceRequest request) {
        List<String> orderProductNumbers = request.getOrderProductInfos().stream()
                .map(OrderProductInfo::getProductNumber)
                .collect(Collectors.toList());

        List<Product> repositoryProducts = productRepository.findByProductNumberIn(orderProductNumbers);
        if (repositoryProducts.isEmpty()) {
            throw new RuntimeException("일치하는 상품이 존재하지 않습니다.");
        }

        return repositoryProducts;
    }
    @Transactional(readOnly = true)
    public Long getOrderProductStock(OrderServiceRequest request, Product product) {
        Optional<OrderProductInfo> optionalOrderProductInfo = request.getOrderProductInfos().stream()
                .filter(info -> info.getProductNumber().equals(product.getProductNumber()))
                .findFirst();

        return optionalOrderProductInfo.get().getStock();
    }
    @Transactional
    public Long updateOrderProductAndGetTotalPrice(OrderServiceRequest request, Order order) {
        List<Product> products = getRepositoryProducts(request);

        Long totalPrice = 0L;
        for (Product product : products) {
            Long orderProductStock = getOrderProductStock(request, product);

            // orderproduct count 추가
            order.getOrderProducts().stream() // 메모리 참조
                    .filter(orderProduct -> orderProduct.getProduct().getProductNumber().equals(product.getProductNumber()))
                    .findFirst().get().updateCount(orderProductStock);

            // product count 뺴기
            product.decreaseStockAndIncreaseSales(orderProductStock);
            totalPrice += product.getPrice() * orderProductStock;
        }

        return totalPrice;
    }

}
