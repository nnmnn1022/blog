package com.umoo.board.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class Base {

    @CreatedDate // 엔터티 생성 시 시간 자동 저장
    @Column(updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 엔티티 값 수정 시 시간 자동 저장
    private LocalDateTime modDate;

    /**
     * 아래 내용은 로그인 구현할 때 다시 구현
     */
//    @CreatedBy
//    @Column(updatable = false)
//    private User? regDate;
//
//    @LastModifiedBy
//    private LocalDateTime modDate;


}
