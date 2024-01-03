package kr.hanghae.deploy.controller;

import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import kr.hanghae.deploy.dto.order.controller.request.OrderRequest;
import kr.hanghae.deploy.dto.order.service.response.OrderResponse;
import kr.hanghae.deploy.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ApiResponse<OrderResponse> saveOrder(@RequestBody OrderRequest request) {
        String username = request.getUsername();
        List<OrderProductInfo> orderProductInfos = request.getOrderProductInfos();
        return ApiResponse.ok(orderService.saveOrder(username, orderProductInfos));
    }
}
