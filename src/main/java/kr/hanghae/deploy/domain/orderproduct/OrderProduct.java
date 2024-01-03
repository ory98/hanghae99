package kr.hanghae.deploy.domain.orderproduct;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import kr.hanghae.deploy.domain.BaseEntity;
import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDER_PRODUCT")
@Entity
public class OrderProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Long count;

    @Builder
    public OrderProduct(Order order,Product product) {
        this.order = order;
        this.product = product;
    }

    public void updateCount(Long count) {
        this.count = count;
    }
}

