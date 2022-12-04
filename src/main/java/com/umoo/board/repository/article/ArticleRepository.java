package com.umoo.board.repository.article;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umoo.board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<엔티티 이름, primaryKey Type>
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByTitleContaining(String searchKeyword, Pageable pageable);
    Page<Article> findAllByIsDel(boolean isDel, Pageable pageable);

}