package com.umoo.board.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class File extends Base{
    @Id
    @Column(name = "file_name")
    private String fileName;
    private String filePath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;

}
