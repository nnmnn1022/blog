package com.umoo.board.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// lombok의 Data를 쓰는데, 없을 시 getter / setter를 만들어줘야 함
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id") // GenerationType.IDENTITY 설정 후 DB에 column을 만들 때도 AI(Auto Increment)에 체크 해줘야 함
    private Long id;
    private Long categoryId;
//    private Long authorId;
    private String title;
    private String content;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<File> files = new ArrayList<>();
//    private Integer readCnt;
    private Boolean isDel;
    private Boolean isTop;

    @PrePersist
    public void prePersist(){
        this.isDel =  this.isDel == null ? false : this.isDel ;
        this.isTop =  this.isTop == null ? false : this.isDel ;
    }
}
