package kr.hanghae.deploy.domain.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문된 상품에서 일치하는 상품이 있는지 없으면 실패한다.")
    void checkExistOrderProduct() {
        // todo
        // given : 테스트를 위해 주어질 값들
        Product product1 = new Product("1" , "상품1", 1000L, 10L);
        Product product2 = new Product("2" , "상품2", 1000L, 10L);
        Product product3 = new Product("3" , "상품3", 1000L, 10L);

        // when : 어떤 것을 테스트할지 정의
        List<Product> productList = productRepository.findByProductNumberIn(new ArrayList<String>(Arrays.asList("1","2","3")));

        // then : 값에 대한 검증
//        assertThat(productList[i])
    }
}
