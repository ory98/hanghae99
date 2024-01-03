package kr.hanghae.deploy.domain.point;

import jakarta.persistence.*;
import kr.hanghae.deploy.domain.BaseEntity;
import kr.hanghae.deploy.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point")
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long point;

    @Enumerated(EnumType.STRING)
    private PointState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Point(Long point, PointState state, User user) {
        this.point = point;
        this.state = state;
        this.user = user;
    }
}
