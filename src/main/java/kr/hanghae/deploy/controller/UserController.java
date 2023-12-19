package kr.hanghae.deploy.controller;

import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.user.controller.request.UserRequest;
import kr.hanghae.deploy.dto.user.service.response.UserInfoResponse;
import kr.hanghae.deploy.dto.user.service.response.UserResponse;
import kr.hanghae.deploy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ApiResponse<UserInfoResponse> getUserInfo(@RequestBody UserRequest request) {
        return ApiResponse.ok(userService.getUserInfo(request.toService()));
    }

    @PostMapping("/user/join")
    public ApiResponse<UserResponse> saveUser(@RequestBody UserRequest request) {
        return ApiResponse.ok(userService.saveUser(request.toService()));
    }



}
