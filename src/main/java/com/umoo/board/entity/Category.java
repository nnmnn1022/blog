package com.umoo.board.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
