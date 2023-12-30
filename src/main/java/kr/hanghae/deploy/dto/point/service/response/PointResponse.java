package kr.hanghae.deploy.dto.point.service.response;

import kr.hanghae.deploy.domain.user.User;
import lombok.Getter;

@Getter
public class PointResponse {

    private String message;

    public PointResponse(User user) {
        this.message = user.getUsername() + "님의 포인트는" + user.getPoint() + "원 입니다.";
    }

    public static PointResponse of(User user) {
        return new PointResponse(user);
    }

}
