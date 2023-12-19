package kr.hanghae.deploy.dto.order.service.request;

import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderServiceRequest {
    private String username;
    private List<OrderProductInfo> orderProductInfos;

    @Builder
    public OrderServiceRequest(String username, List<OrderProductInfo> orderProductInfos) {
        this.username = username;
        this.orderProductInfos = orderProductInfos;
    }
}
