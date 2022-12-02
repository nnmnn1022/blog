package com.umoo.board.repository.article;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umoo.board.entity.Article;
import com.umoo.board.entity.QArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.umoo.board.entity.QArticle.article;

@RequiredArgsConstructor
public class ArticleRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Article> searchAll(Article article, Pageable pageable){
        List<Article> result = jpaQueryFactory
                .selectFrom(QArticle.article)
                .where(noDel())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression noDel(){
        return article.isDel.eq(false);
    }
}
