package kr.hanghae.deploy.service.point;

import kr.hanghae.deploy.domain.point.Point;
import kr.hanghae.deploy.domain.point.PointRepository;
import kr.hanghae.deploy.domain.point.PointState;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.point.service.request.PointRequestService;
import kr.hanghae.deploy.dto.point.service.response.PointResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PointService {

    private final UserRepository userRepository;

    @Transactional
    public PointResponse updatePoint(PointRequestService request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("회원 정보가 존재하지 않습니다.");
        }

        User user = optionalUser.get();
        user.increasePoint(request.getPoint());

        Point.builder()
                .point(request.getPoint())
                .state(PointState.PLUS)
                .user(user)
                .build();

        log.info(user.getUsername() + " 회원의 포인트을 충전하였습니다. 현재 유저 포인트 : " + user.getPoint());

        return PointResponse.of(optionalUser.get());

    }
}
