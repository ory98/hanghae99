package kr.hanghae.deploy.service.user;

import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.user.service.request.UserRequestService;
import kr.hanghae.deploy.dto.user.service.response.UserInfoResponse;
import kr.hanghae.deploy.dto.user.service.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserInfoResponse getUserInfo(UserRequestService request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("회원 정보가 존재하지 않습니다.");
        }

        return UserInfoResponse.of(optionalUser.get());
    }

    @Transactional
    public UserResponse saveUser(UserRequestService request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        User savedUser = userRepository.save(new User(request.getUsername()));

        return UserResponse.of(savedUser.getUsername());
    }

}
