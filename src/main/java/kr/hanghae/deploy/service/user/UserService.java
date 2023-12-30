package kr.hanghae.deploy.service.user;

import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.user.controller.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserInfo(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("회원 정보가 존재하지 않습니다.");
        }

        return optionalUser.get();
    }

    @Transactional
    public String saveUser(UserRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        User savedUser = userRepository.save(new User(request.getUsername()));

        return savedUser.getUsername();
    }

}
