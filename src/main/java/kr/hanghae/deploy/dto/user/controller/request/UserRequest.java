package kr.hanghae.deploy.dto.user.controller.request;

import kr.hanghae.deploy.dto.user.service.request.UserRequestService;
import lombok.Getter;

@Getter
public class UserRequest {
    private String username;

    public UserRequestService toService() {
        return new UserRequestService(
                this.username
        );
    }

}
