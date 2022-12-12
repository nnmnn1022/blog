package com.umoo.board.condition;

import lombok.Data;

@Data
public class CategorySearchCondition {
    private Long id;
    private Long parentId;
    private String name;
    private Boolean isDel;
    private Boolean isTop;
    private Boolean isView;
}
