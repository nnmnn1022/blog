package com.umoo.board.service;

import com.umoo.board.condition.CategorySearchCondition;
import com.umoo.board.entity.Category;
import com.umoo.board.repository.Category.CategoryQueryRepository;
import com.umoo.board.repository.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryQueryRepository categoryQueryRepository;

    public void add(Category category){

        categoryRepository.save(category);
    }

    public List<Category> list(){
        // condition 객체 생성
        CategorySearchCondition condition = new CategorySearchCondition();
        condition.setIsView(true);
        List<Category> depth0 = new ArrayList<>();
        List<Category> depth1 = new ArrayList<>();
        List<Category> depth2 = new ArrayList<>();

        List<Category> newList = new ArrayList<>();
        List<Category> categories = categoryQueryRepository.searchByWhere(condition);
//        categories.forEach(category -> {
//            if (category.getDepth() == 0) depth0.add(category);
//            if (category.getDepth() == 1) depth1.add(category);
//            if (category.getDepth() == 2) depth2.add(category);
//        });
//
//        depth0.forEach(category0 -> {
//            newList.add(category0);
//            depth1.forEach(category1 -> {
//                if (category1.getParentId() == category0.getId()) {
//                    newList.add(category1);
//                    depth2.forEach(category2 -> {
//                        if (category2.getParentId() == category1.getId()){
//                            newList.add(category2);
//                        }
//                    });
//                }
//
//            });
//        });
        return newList;
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
