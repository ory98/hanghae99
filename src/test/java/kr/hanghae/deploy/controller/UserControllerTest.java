package kr.hanghae.deploy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.user.service.response.UserInfoResponse;
import kr.hanghae.deploy.dto.user.service.response.UserResponse;
import kr.hanghae.deploy.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 이름을 받아서 저장한 후, 유저 이름을 반환한다")
    void getUserInfo() throws Exception {
        // given
        UserInfoResponse userInfo = new UserInfoResponse(new User("test"));
        ApiResponse response = ApiResponse.ok(userInfo);

        given(userService.getUserInfo(any())).willReturn(userInfo);

        // when
        ResultActions actions = mockMvc.perform(get("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(response)));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.username").value("test"))
                .andExpect(jsonPath("$.data.point").value("0"))
                .andExpect(jsonPath("$.data.orders").isEmpty())
                .andDo(print());

    }

    @Test
    @DisplayName("유저이름을 받아 유저 정보를 조회한다")
    void saveUser() throws Exception {
        // given
        UserResponse user = new UserResponse(new User("test").getUsername());
        ApiResponse response = ApiResponse.ok(user);

        given(userService.saveUser(any())).willReturn(UserResponse.of(user.getUsername()));

        // when
        ResultActions actions = mockMvc.perform(post("/user/join")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.username").value("test"))
                .andDo(print());
    }
}