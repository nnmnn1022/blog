package com.umoo.board.DTO;

import com.umoo.board.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private Boolean isDel;
    private Boolean isTop;
    private Boolean isView;
    private Integer depth;
    private List<CategoryDTO> children;

    public static CategoryDTO of(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getIsDel(),
                category.getIsTop(),
                category.getIsView(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryDTO::of).collect(Collectors.toList())
        );
    }
}
