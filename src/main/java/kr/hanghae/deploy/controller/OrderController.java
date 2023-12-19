package kr.hanghae.deploy.controller;

import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.order.controller.request.OrderRequest;
import kr.hanghae.deploy.dto.order.service.response.OrderResponse;
import kr.hanghae.deploy.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ApiResponse<OrderResponse> saveOrder(@RequestBody OrderRequest request) {
        return ApiResponse.ok(orderService.saveOrder(request.toService()));
    }
}
