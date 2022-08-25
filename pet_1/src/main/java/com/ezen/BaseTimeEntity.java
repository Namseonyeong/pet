package com.ezen;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

	//Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    //조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}