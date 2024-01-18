package kr.hanghae.deploy.service.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointServiceConcurrencyTest {

    @Test
    @DisplayName("동시에 사용자의 포인트를 충전할 경우 충전에 실패한다.")
    void pointConcurrencyPointTest_fail() throws InterruptedException {
        // todo
        // given: 테스트를 위해 주어질 값들
        // when: 어떤 것을 테스트할지 정의
        // then: 값에 대한 검증
    }

    @Test
    @DisplayName("사용자의 포인트를 충전할 경우 충전에 성공한다.")
    void pointConcurrencyPointTest_success() throws InterruptedException {
        // todo
        // given: 테스트를 위해 주어질 값들
        // when: 어떤 것을 테스트할지 정의
        // then: 값에 대한 검증
    }

}
