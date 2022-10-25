package com.umoo.board.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File extends Base{
    @Id
    @Column(name = "file_name")
    private String fileName;
    private String filePath;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

}
