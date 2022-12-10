package com.umoo.board.controller;

import com.umoo.board.entity.Category;
import com.umoo.board.service.CategoryService;
import com.umoo.board.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 카테고리 조회
     */
    @GetMapping("/category/list") //blog.umoo.pe.kr/
    public String Category() {
        List<Category> list = categoryService.list();

        return "redirect:article/list";
    }

    /**
     * 카테고리 추가
     */

    /**
     * 카테고리 수정
     */

    /**
     * 카테고리 삭제
     */
}
