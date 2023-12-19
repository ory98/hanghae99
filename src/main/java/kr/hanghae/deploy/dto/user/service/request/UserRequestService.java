package kr.hanghae.deploy.dto.user.service.request;

import lombok.Getter;

@Getter
public class UserRequestService {

    private String username;

    public UserRequestService(String username) {
        this.username = username;
    }
}
