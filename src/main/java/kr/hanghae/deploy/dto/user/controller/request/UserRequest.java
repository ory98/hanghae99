package kr.hanghae.deploy.dto.user.controller.request;

import lombok.Getter;

@Getter
public class UserRequest {

    private String username;

    public UserRequest(String username) {
        this.username = username;
    }

}
