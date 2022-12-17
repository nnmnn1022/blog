package com.umoo.board.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "parent")
    private Category parent;
    private String name;
    private Boolean isDel;
    private Boolean isTop;
    private Boolean isView;
    private Integer depth;
    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.isDel =  this.isDel == null ? false : this.isDel ;
        this.isTop =  this.isTop == null ? false : this.isDel ;
        this.isView =  this.isView == null ? true : this.isView ;
    }
}
