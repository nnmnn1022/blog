package com.umoo.board.entity;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// lombok의 Data를 쓰는데, 없을 시 getter / setter를 만들어줘야 함
@Entity
@Data
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationType.IDENTITY 설정 후 DB에 column을 만들 때도 AI(Auto Increment)에 체크 해줘야 함
    private Long id;
    private Long categoryId;
//    private Long authorId;
    private String title;
    private String content;
//    private Integer readCnt;
//    private Boolean isTop;
//    private Integer fileId;
}
