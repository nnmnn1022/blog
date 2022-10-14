package com.umoo.board.controller;

import com.umoo.board.entity.Article;
import com.umoo.board.entity.Category;
import com.umoo.board.service.ArticleService;
import com.umoo.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 게시글 작성 폼 GET
     * articleForm 페이지
     */
    @GetMapping("/article/write") //domain.com/board/write
    public String articleWrite(Model model) {
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "articleForm";
    }

    /**
     * 게시글 작성 후 POST
     * articleList 페이지
     */
    @PostMapping("/article/write")
    public String articleWrite(Model model, Article article) {
        articleService.write(article);
        return  "redirect:/article/list";
    }

    /**
     * 게시글 수정 폼 GET
     * articleForm 페이지
     */
    @GetMapping("/article/modify/{id}")
    public String articleModify(Model model, @PathVariable("id") Long id) {
        model.addAttribute("article", articleService.view(id));
        return "articleModify";
    }

    /**
     * 게시글 수정 후 POST
     * articleView
     */
    @PostMapping("/article/modify/{id}")
    public String articleModify(@PathVariable("id") Long id, Article article) {
        // 준영속 상태의 엔티티를 DB에서 객체로 가져와 수정하고 다시 저장하는 방식이므로 권장되는 방식이 아님
        // 이후 로직 변경이 필요할 것
        Article tmpArticle = articleService.view(id);
        tmpArticle.setTitle(article.getTitle());
        tmpArticle.setContent(article.getContent());

        articleService.write(tmpArticle);

        return "redirect:/article/list";
    }



    /**
     * 게시글 목록 GET
     * articleList 페이지
     */
    @GetMapping("/article/list")
    public String articleList(Model model) {

        model.addAttribute("articles", articleService.list());
        return "articleList";
    }

    /**
     * 게시글 상세 GET
     * @
     */
    @GetMapping("/article/view/{id}") // domain.com/article/view?id=1
    public String articleView(Model model, @PathVariable("id") Long id) {

        model.addAttribute("article", articleService.view(id));
        return "articleView";
    }

    /**
     * 게시글 삭제 GET
     * @
     */
    @GetMapping("/article/delete/{id}") // domain.com/article/view?id=1
    public String articleDelete(@PathVariable("id") Long id) {

        articleService.delete(id);
        return  "redirect:/article/list";
    }


}
