package kr.hanghae.deploy.domain.order;

import jakarta.persistence.*;
import kr.hanghae.deploy.domain.BaseEntity;
import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private OrderStatus orderStatus;

    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    public Order(List<Product> products, String orderNumber, OrderStatus orderStatus, Long userId) {
        this.orderNumber = orderNumber;
        this.totalPrice = 0L;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.orderProducts = products.stream()
                .map(product -> new OrderProduct(this, product))
                .collect(Collectors.toList());
    }

}
