package kr.hanghae.deploy.domain.orderproduct;

import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
