package kr.hanghae.deploy.controller;

import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.user.controller.request.UserRequest;
import kr.hanghae.deploy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ApiResponse<User> getUserInfo(@RequestBody UserRequest request) {
        return ApiResponse.ok(userService.getUserInfo(request.getUsername()));
    }

    @PostMapping("/user/join")
    public ApiResponse<String> saveUser(@RequestBody UserRequest request) {
        return ApiResponse.ok(userService.saveUser(request));
    }

}
