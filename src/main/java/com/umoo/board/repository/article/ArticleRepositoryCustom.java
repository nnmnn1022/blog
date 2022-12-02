package com.umoo.board.repository.article;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umoo.board.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArticleRepositoryCustom {
    Page<Article> searchAll(Article article, Pageable pageable);
}

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {}
