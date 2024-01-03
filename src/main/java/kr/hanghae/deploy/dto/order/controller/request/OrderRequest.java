package kr.hanghae.deploy.dto.order.controller.request;

import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import kr.hanghae.deploy.dto.order.service.request.OrderServiceRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private String username;
    private List<OrderProductInfo> orderProductInfos;

    public OrderServiceRequest toService() {
        return OrderServiceRequest.builder()
                .username(username)
                .orderProductInfos(orderProductInfos)
                .build();
    }
}
