package kr.hanghae.deploy.domain.user;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    Optional<User> findByUsername(String username);

}
