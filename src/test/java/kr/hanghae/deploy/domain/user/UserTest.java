package kr.hanghae.deploy.domain.user;

import kr.hanghae.deploy.domain.order.OrderRepository;
import kr.hanghae.deploy.service.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName("주문 총 금액보다 사용자의 포인트가 많거나 같으면 성공한다.")
    void checkUserPoint_success() {
        // given : 테스트를 위해 주어질 값들
        // when : 어떤 것을 테스트할지 정의
        // then : 값에 대한 검증
    }

    @Test
    @DisplayName("주문 총 금액보다 사용자의 포인트가 적으면 실패한다.")
    void checkUserPoint_fail() {
        // given : 테스트를 위해 주어질 값들
        // when : 어떤 것을 테스트할지 정의
        // then : 값에 대한 검증
    }

}
