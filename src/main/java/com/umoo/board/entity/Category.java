package com.umoo.board.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String name;
    private Boolean isDel;
    private Boolean isTop;
    private Boolean isView;
    private Integer depth;

    @PrePersist
    public void prePersist(){
        this.isDel =  this.isDel == null ? false : this.isDel ;
        this.isTop =  this.isTop == null ? false : this.isDel ;
        this.isView =  this.isView == null ? true : this.isView ;
    }
}
