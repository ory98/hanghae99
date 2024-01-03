package kr.hanghae.deploy.service.order;

import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.order.OrderRepository;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.product.ProductRepository;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import kr.hanghae.deploy.dto.order.service.response.OrderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문 생성에 대한 통합테스트")
    void saveOrder() {

        // given : 테스트를 위해 주어질 값들
        userRepository.saveAndFlush(new User("test", 10000L));
        productRepository.saveAndFlush(new Product("1234", "짬뽕", 100L, 10L));

        // when : 어떤 것을 테스트할지 정의
        OrderResponse order = orderService.saveOrder("test",  List.of(new OrderProductInfo("1234", 3L)));

        // then : 값에 대한 검증
        assert order != null;
    }

}
