package kr.hanghae.deploy.dto.user.service.response;

import lombok.Getter;

@Getter
public class UserResponse {

   private String username;

   public UserResponse(String username) {
      this.username = username + " 님 회원가입되셨습니다.";
   }

   public static UserResponse of(String username) {
      return new UserResponse(username);
   }

}
