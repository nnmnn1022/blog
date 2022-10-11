package com.umoo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/article/write") //domain/board/write
    public String articleForm() {
        return "articleForm";
    }

    @PostMapping("/article/write")
    public String articleWrite(String title, String content) {

        return "";
    }
}
