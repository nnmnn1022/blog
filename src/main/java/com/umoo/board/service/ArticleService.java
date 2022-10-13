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

    public List<Article> list(){

        return articleRepository.findAll();
    }

    public Article view(Long id){

        return articleRepository.findById(id).get();
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }

}
