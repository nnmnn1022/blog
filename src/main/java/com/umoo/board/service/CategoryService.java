package com.umoo.board.service;

import com.umoo.board.condition.CategorySearchCondition;
import com.umoo.board.entity.Category;
import com.umoo.board.repository.Category.CategoryQueryRepository;
import com.umoo.board.repository.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryQueryRepository categoryQueryRepository;

    public void write(Category category){

        categoryRepository.save(category);
    }

    public List<Category> list(){
        // condition 객체 생성
        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setIsView(true);
        List<Category> list;
        List<Integer> group;
        List<Category> categories = categoryQueryRepository.searchByWhere(condition);
        categories.forEach(category -> {
            if (category.getDepth() == 0){
                Long groupId = category.getId();
            }

        });
        return
    }

    public List<Category> allList(){
        return categoryRepository.findAllByIsDel(false);
    }

    public Category view(Long id){

        return categoryRepository.findById(id).get();
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

}
