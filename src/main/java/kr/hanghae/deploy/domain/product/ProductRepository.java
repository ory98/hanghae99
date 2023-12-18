package kr.hanghae.deploy.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.productNumber in :productNumbers")
    List<Product> findByProductNumbers(@Param("productNumbers") List<String> productNumbers);
}
