package kr.hanghae.deploy.service.order;

import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.order.OrderRepository;
import kr.hanghae.deploy.domain.order.OrderStatus;
import kr.hanghae.deploy.domain.point.Point;
import kr.hanghae.deploy.domain.point.PointState;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.product.ProductRepository;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import kr.hanghae.deploy.dto.order.service.request.OrderServiceRequest;
import kr.hanghae.deploy.dto.order.service.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderResponse saveOrder(OrderServiceRequest request) {

        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("유저 정보가 잘못된 정보입니다.");
        }

        User user = optionalUser.get();

        List<String> productNumbers = request.getOrderProductInfos().stream()
                .map(OrderProductInfo::getProductNumber)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findByProductNumbers(productNumbers);

        if (products.isEmpty()) {
            throw new RuntimeException("일치하는 상품이 존재하지 않습니다.");
        }


        Long totalPrice = 0L;

        for (Product product : products) {
            Optional<OrderProductInfo> optionalOrderProductInfo = request.getOrderProductInfos().stream()
                    .filter(info -> info.getProductNumber().equals(product.getProductNumber()))
                    .findFirst(); // request로 넘어온 OrderProductInfos를 가져옴

            // 존재 체크
            if (optionalOrderProductInfo.isEmpty()) {
                throw new RuntimeException(String.format("%d 은 존재하지 않는 상품입니다.", optionalOrderProductInfo.get().getProductNumber()));
            }

            // 재고 체크
            OrderProductInfo orderProductInfo = optionalOrderProductInfo.get();
            if (product.getStock() < orderProductInfo.getStock()) {
                throw new RuntimeException(
                        String.format("%s 상품은 재고량 부족입니다. 현재 재고량 : %s개",
                                product.getName(),
                                product.getStock()
                        ));
            }

            product.decreaseStock(orderProductInfo.getStock());
            totalPrice += product.getPrice() * orderProductInfo.getStock();
        }

        // 포인트 체크
        if (user.getPoint() < totalPrice) {
            throw new RuntimeException(
                    String.format("point가 부족합니다. 현재 point : %d 원 , 결제가격 : %d 원",
                            user.getPoint(),
                            totalPrice
                    ));
        }

        // 저장
        Order newOrder = Order.builder()
                .products(products)
                .orderNumber(UUID.randomUUID().toString())
                .totalPrice(totalPrice)
                .orderStatus(OrderStatus.INIT)
                .user(user)
                .build();

        user.decreasepoint(totalPrice);

        Point.builder()
                .point(totalPrice)
                .state(PointState.MINUS)
                .user(user)
                .build();

        orderRepository.save(newOrder);


        return null;
    }

}
