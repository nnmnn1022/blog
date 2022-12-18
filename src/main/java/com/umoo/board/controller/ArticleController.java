package com.umoo.board.controller;

import com.umoo.board.entity.Article;
import com.umoo.board.entity.Category;
import com.umoo.board.entity.File;
import com.umoo.board.logic.Common;
import com.umoo.board.logic.PageRequest;
import com.umoo.board.service.ArticleService;
import com.umoo.board.service.CategoryService;
import com.umoo.board.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileService fileService;

    @GetMapping("/") //blog.umoo.pe.kr/
    public String index() {
        return "redirect:article/list";
    }

    /**
     * 게시글 작성 폼 GET
     * articleForm 페이지
     */
    @GetMapping("/article/write") //blog.umoo.pe.kr/board/write
    public String articleWrite(Model model) {
        // 폼데이터 가져오기
//        List<Category> categories = categoryService.list();
//        model.addAttribute("categories", categories);
        return "article/articleForm";
    }

    /**
     * 게시글 작성 후 POST
     * articleList 페이지
     * MultipartFile 변수 이름과 Template의 name 속성값을 일치시킬 것!
     */
    @PostMapping("/article/write")
    public String articleWrite(Article article, MultipartFile file) throws Exception{

        articleService.write(article, file);

        return  "redirect:/article/list";
    }

    /**
     * 게시글 목록 GET
     * articleList 페이지
     * org.springframgework.data.domain.Pageable 을 사용한 페이징
     */
//    @GetMapping("/article/list")
//    public String articleList(Model model,
//                              @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//                              String searchKeyword) {
//
//        Page<Article> list = null;
//
//        /*
//        searchKeyword 변수의 유무를 확인하여 전체 페이지 / 검색 결과 페이지 반환
//         */
//        if (searchKeyword == null){
//            list = articleService.list(true, pageable);
//        }else {
////            list = articleService.articleSearchList(searchKeyword, pageable);
////            list = articleService.list
//        }
//
//        /*
//        페이징 처리
//         */
//        int curPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(1, curPage - 4);
//        int endPage = Math.min(curPage + 5, list.getTotalPages());
//
//        // 카테고리 가져오기
////        List<Category> categories = categoryService.list();
////
////        model.addAttribute("categories", categories);
//        model.addAttribute("articles", list);
//        model.addAttribute("curPage", curPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "article/articleList";
//
//    }
    @GetMapping("/article/list")
    public String articleListByCategory(Model model,
                                        PageRequest pageRequest,
                                        Long categoryId) {

        PageImpl<Article> list = null;
        Pageable pageable = pageRequest.of();

        list = articleService.ListByCategory(categoryId, pageable);

        /*
        페이징 처리
         */
        int curPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(1, curPage - 4);
        int endPage = Math.min(curPage + 5, list.getTotalPages());

        model.addAttribute("articles", list);
        model.addAttribute("curPage", curPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        if (categoryId != null){
            model.addAttribute("categoryId", categoryId);
        }

        return "article/articleList";
    }

    /**
     * 게시글 수정 폼 GET
     * articleForm 페이지
     */
    @GetMapping("/article/modify/{id}")
    public String articleModify(Model model, @PathVariable("id") Long id) {
//        List<Category> categories = categoryService.list();
        Collection<File> file = fileService.view(id);
//        model.addAttribute("categories", categories);
        model.addAttribute("article", articleService.view(id));
        return "article/articleModify";
    }

    /**
     * 게시글 수정 후 POST
     * articleModify >> article list 반환
     */
    @PostMapping("/article/modify/{id}")
    public String articleModify(@PathVariable("id") Long id, Article article, MultipartFile file) throws Exception {
        // 준영속 상태의 엔티티를 DB에서 객체로 가져와 수정하고 다시 저장하는 방식이므로 권장되는 방식이 아님
        // 이후 로직 변경이 필요할 것
        Article tmpArticle = articleService.view(id);
        tmpArticle.setCategoryId(article.getCategoryId());
        tmpArticle.setTitle(article.getTitle());
        tmpArticle.setContent(article.getContent());
        articleService.write(tmpArticle, file);

        return "redirect:/article/view/" + id;
    }


    /**
     * 게시글 상세 GET
     * ArticleView
     */
    @GetMapping("/article/view/{id}") // domain.com/article/view?id=1
    public String articleView(Model model, @PathVariable("id") Long id) {
        Article article = articleService.view(id);
        Common com = new Common();

        model.addAttribute("article", article);
        model.addAttribute("date", com.getDate(article.getRegDate()));
        return "article/articleView";
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
