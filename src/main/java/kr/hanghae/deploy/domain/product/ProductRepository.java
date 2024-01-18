package kr.hanghae.deploy.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductNumber(String productNumber);

    List<Product> findByProductNumberIn(List<String> productNumbers);

}
