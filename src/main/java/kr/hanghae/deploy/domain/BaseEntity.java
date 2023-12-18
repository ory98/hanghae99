package kr.hanghae.deploy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "CREATE_DATE")
    @CreatedDate
    private LocalDateTime createdDateTime;

    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}