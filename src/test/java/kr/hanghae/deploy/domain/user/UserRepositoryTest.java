package kr.hanghae.deploy.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class UserRepositoryTest { // entity Test 호환이 잘 되는지

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 이름을 통해 유저가 존재하는지 검증한다")
    void findByUsername() { // 올바른 값을 잘 가져오는지

        // given: 테스트를 위해 주어질 값들
        User user = new User("A");
        userRepository.saveAndFlush(user); // saveAndFlush DB에 바로 커밋

        // when: 어떤 것을 테스트할지 정의
        // todo: 결과가 두개 나옴 수정 요망
        User findUser = userRepository.findByUsername("A").get();

        // then: 값에 대한 검증을 하는 단계
        assertThat(findUser.getUsername()).isEqualTo("A");
    }
}