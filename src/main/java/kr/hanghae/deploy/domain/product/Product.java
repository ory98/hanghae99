package kr.hanghae.deploy.domain.product;

import jakarta.persistence.*;
import kr.hanghae.deploy.domain.BaseEntity;
import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.order.OrderStatus;
import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "PRODUCT")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NUMBER")
    private String productNumber;

    private String name;

    private Long price;

    private Long stock;

    private Long sales;

    public Product(String productNumber, String name, Long price, Long stock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sales = 0L;
    }

    public void decreaseStockAndIncreaseSales(Long stock) {
        if(this.stock < stock) {
            throw new RuntimeException(
                    String.format("%s 상품은 재고량 부족입니다. 현재 재고량 : %d개",
                            this.name,
                            this.stock
                    ));
        }
        this.stock -= stock;
        this.sales += stock;
    }
}
