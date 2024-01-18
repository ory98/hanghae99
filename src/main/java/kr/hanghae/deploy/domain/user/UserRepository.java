package kr.hanghae.deploy.domain.user;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Lock(value = LockModeType.OPTIMISTIC)
//    질문: testcode error- TransactionRequiredException: no transaction is in progress 락의 범위와 트랜잭션의 범위는 동등해야한다,, 그렇다면 어떻게 하는것이 좋은가
    Optional<User> findByUsername(String username);

}
