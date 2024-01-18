package kr.hanghae.deploy.domain.order;

import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderedEvent {

    private Long orderId;
    private List<OrderProduct> orderProductList;
    private Long totalPrice;

    public OrderedEvent(Long orderId, List<OrderProduct> orderProductList, Long totalPrice) {
        this.orderId = orderId;
        this.orderProductList = orderProductList;
        this.totalPrice = totalPrice;
    }
}
