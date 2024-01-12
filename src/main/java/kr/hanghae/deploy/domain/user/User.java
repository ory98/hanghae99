package kr.hanghae.deploy.domain.user;

import jakarta.persistence.*;
import kr.hanghae.deploy.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Long point;

    @Version
    private Long version;

    public User(String username) {
        this.username = username;
        this.point = 0L;
    }

    public User(String username, Long point) {
        this.username = username;
        this.point = point;
    }

    public void increasePoint(Long point) {
        this.point += point;
    }

    public void decreasePoint(Long point) {
        if (this.point < point) {
            throw new RuntimeException(
                    String.format("point가 부족합니다. 현재 point : %d 원 , 결제가격 : %d 원",
                            this.point,
                            point
                    ));
        }
        this.point -= point;
    }
}
