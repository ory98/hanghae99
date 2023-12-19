package kr.hanghae.deploy.dto.order.service.response;

import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.dto.user.service.response.UserResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {

    private String username;
    private List<Product> productList;
    private String point;

    public OrderResponse(String username, List<Product> productList, String point) {
        this.username = username;
        this.productList = productList;
        this.point = point;
    }

    public static OrderResponse of(String username, List<Product> productList, String point) {
        return new OrderResponse(username, productList, point);
    }
}
