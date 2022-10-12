package com.umoo.board.service;

import com.umoo.board.entity.Article;
import com.umoo.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public void write(Article article){

        articleRepository.save(article);
    }

    public List<Article> List(){

        return articleRepository.findAll();
    }

    public Article View(Long id){

        return articleRepository.findById(id).get();
    }

    public void Delete(Long id){
        articleRepository.deleteById(id);
    }

}
