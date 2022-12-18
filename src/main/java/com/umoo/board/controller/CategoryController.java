package com.umoo.board.controller;

import com.umoo.board.entity.Category;
import com.umoo.board.service.CategoryService;
import com.umoo.board.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 카테고리 조회
     */
    @GetMapping("/category/list") //blog.umoo.pe.kr/
    public String category(Model model) {
        List<Category> list = categoryService.allList();
        model.addAttribute("categories", list);

        return "category/categoryForm";
    }

    /**
     * 카테고리 조회
     */
    @GetMapping("/category/settings") //blog.umoo.pe.kr/
    public String categorySetting() {

        return "category/categoryForm";
    }

    /**
     * 카테고리 추가
     */
    @PostMapping("/category/add")
    public String categoryAdd(Category category) {
        categoryService.add(category);

        return "redirect:/category/settings";
    }

    /**
     * 카테고리 수정
     */
    @GetMapping("/category/modify")
    public String categoryModify(List<Category> categoryList) {
//        categoryService.(category);

        return "redirect:/category/settings";
    }

    /**
     * 카테고리 삭제
     */
}
