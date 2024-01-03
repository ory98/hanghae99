package kr.hanghae.deploy.dto.order.service.request;

import kr.hanghae.deploy.dto.order.OrderProductInfo;
import kr.hanghae.deploy.dto.user.controller.request.UserRequest;
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

    public static UserRequest toUserRequest(String username) {
        return new UserRequest(username);
    }
}
