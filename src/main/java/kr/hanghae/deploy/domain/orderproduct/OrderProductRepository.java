package kr.hanghae.deploy.domain.orderproduct;

import kr.hanghae.deploy.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
