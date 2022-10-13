package com.umoo.board.Service;

import com.umoo.board.entity.Article;
import com.umoo.board.repository.ArticleRepository;
import com.umoo.board.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void write() {
    }

    @Test
    void list() {
    }

    @Test
    void view() {
        //given
        Long id = 1L;

        //when
        Article article = articleService.view(id);

        //then
        System.out.println("article = " + article);
    }

    @Test
    void delete() {
    }
}