package kr.hanghae.deploy.dto.order.service.response;

import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.product.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {

    private String username;
    private List<OrderProduct> orderProductList;
    private Long point;

    public OrderResponse(String username, List<OrderProduct> orderProductList, Long point) {
        this.username = username;
        this.orderProductList = orderProductList;
        this.point = point;
    }

    public static OrderResponse of(String username, List<OrderProduct> orderProductList, Long point) {
        return new OrderResponse(username, orderProductList, point);
    }
}
