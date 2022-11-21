package com.umoo.board.service;

import com.umoo.board.entity.Category;
import com.umoo.board.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void write(Category category){

        categoryRepository.save(category);
    }

    public List<Category> list(){
        return categoryRepository.findAllByIsDel(false);
    }

    public Category view(Long id){

        return categoryRepository.findById(id).get();
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

}
