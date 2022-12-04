package com.umoo.board.condition;

import com.umoo.board.entity.File;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleSearchCondition {
    Long id;
    Long categoryId;
    String title;
    String content;
    Boolean isDel;
    Boolean isTop;
}
