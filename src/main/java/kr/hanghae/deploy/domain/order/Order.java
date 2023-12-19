package kr.hanghae.deploy.domain.order;

import jakarta.persistence.*;
import kr.hanghae.deploy.domain.BaseEntity;
import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.user.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    public Order(List<Product> products, String orderNumber, Long totalPrice, OrderStatus orderStatus, User user, List<OrderProduct> orderProducts) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.user = user;
        this.orderProducts = products.stream()
                .map(product -> new OrderProduct(this, product))
                .collect(Collectors.toList());
    }
}
