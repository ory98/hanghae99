package kr.hanghae.deploy.service.user;

import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.user.controller.request.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("유저 이름이 존재하는지 확인하고 없으면 저장시킨다")
    void saveUser_success() {
        // given
        User user = new User("test");
        given(userRepository.findByUsername(anyString())).willReturn(Optional.empty());
        given(userRepository.save(any())).willReturn(user);
        UserRequest request = new UserRequest("request");

        // when
        String savedUsername = userService.saveUser(request);

        // then
        assertThat(savedUsername.equals(user.getUsername()));
    }

    @Test
    @DisplayName("유저 이름이 존재하는지 확인하고 존재할 경우 실패한다")
    void saveUse_fail() {
        // given
        User user = new User("test");
        given(userRepository.findByUsername(anyString())).willReturn(Optional.of(user));
        UserRequest request = new UserRequest("request");

        // when // then
        assertThatThrownBy(() -> userService.saveUser(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 존재하는 아이디입니다.");
    }

    @Test
    @DisplayName("유저 이름을 통해 유저의 정보를 가져온다")
    void getUserInfo() {
        // given
        User user = new User("test");
        given(userRepository.findByUsername(anyString())).willReturn(Optional.of(user));

        // when
        User userInfo = userService.getUserInfo(user.getUsername());

        // then
        assertThat(userInfo.getUsername()).isEqualTo(user.getUsername());
    }
}