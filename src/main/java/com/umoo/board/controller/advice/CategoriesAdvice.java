package com.umoo.board.controller.advice;

import com.umoo.board.entity.Category;
import com.umoo.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CategoriesAdvice {

    @Autowired
    CategoryService categoryService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("categories", categoryService.list());
    }
}
