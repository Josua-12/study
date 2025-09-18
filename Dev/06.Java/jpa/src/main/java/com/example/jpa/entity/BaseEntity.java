package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
    모든 엔티티가 상속받을 기본 엔티티
        - 생성일시, 수정일시를 자동으로 관리
        - @MappedSuperclass
            - 이클래스는 테이블로 생성되지 않고, 상속받는 엔티티의 칼럼으로만 포함됨
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  //자동으로 생성일시와 수정일시를 관리하는 엔티티 리스너
public abstract class BaseEntity {
    /*
        엔터티 생성 일시
            - @CreatedDate : 엔티티 생성 시 자동으로 현재 시간 입력
            - updatable = false : 한번 생성되면 수정 불가
     */
    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    /*
        엔터티 수정 일시
            - @LastModifiedDate : 엔티티 수정 시마다 자동으로 현재 시간 갱신
            - 처음 생성 시에도 값이 입력됨 (createdAt과 동일한 값)
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
