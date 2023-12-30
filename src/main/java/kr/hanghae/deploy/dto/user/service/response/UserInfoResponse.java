package kr.hanghae.deploy.dto.user.service.response;

import kr.hanghae.deploy.domain.order.Order;
import kr.hanghae.deploy.domain.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserInfoResponse {

    private String username;

    private Long point;

    private List<Order> orders = new ArrayList<>();

    public UserInfoResponse(User user) {
        this.username = user.getUsername();
        this.point = user.getPoint();
    }

    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(user);
    }

}
