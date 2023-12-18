package kr.hanghae.deploy.service.point;

import kr.hanghae.deploy.domain.point.Point;
import kr.hanghae.deploy.domain.point.PointRepository;
import kr.hanghae.deploy.domain.point.PointState;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.point.service.request.PointRequestService;
import kr.hanghae.deploy.dto.point.service.response.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final UserRepository userRepository;

    @Transactional
    public PointResponse updatePoint(PointRequestService request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("회원 정보가 존재하지 않습니다.");
        }

        User user = optionalUser.get();
        user.increasepoint(request.getPoint());

        Point.builder()
                .point(request.getPoint())
                .state(PointState.PLUS)
                .user(user)
                .build();

        return PointResponse.of(optionalUser.get());

    }
}
