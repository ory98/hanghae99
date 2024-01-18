package kr.hanghae.deploy.dto.user.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {

    private String username;

    public UserRequest(String username) {
        this.username = username;
    }

}
