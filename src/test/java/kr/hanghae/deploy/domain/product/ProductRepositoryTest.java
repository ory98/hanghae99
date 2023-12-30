package kr.hanghae.deploy.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문된 상품에서 일치하는 상품이 있는지 없으면 실패한다.")
    void checkExistOrderProduct() {

        // given : 테스트를 위해 주어질 값들
        // when : 어떤 것을 테스트할지 정의
        // then : 값에 대한 검증

    }
}
