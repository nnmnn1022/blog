package com.umoo.board.repository.article;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umoo.board.condition.ArticleSearchCondition;
import com.umoo.board.entity.Article;
import com.umoo.board.entity.QArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {

    private JPAQueryFactory jpaQueryFactory;

    static final QArticle qArticle = new QArticle("qArticle");

    public PageImpl<Article> searchByWhere(ArticleSearchCondition condition, Pageable pageable){
        List<Article> articleList = jpaQueryFactory.selectFrom(qArticle)
                .where(
                        categoryIdEq(condition.getCategoryId()),
                        isDelEq(false)
                )
                .orderBy(
                        qArticle.id.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        /*total Count 쿼리를 직접 날린다.*/
        long total = jpaQueryFactory.selectFrom(qArticle)
                .where(
                        categoryIdEq(condition.getCategoryId()),
                        isDelEq(false)
                )
                .stream().count();

        return new PageImpl<>(articleList, pageable, total);
    }

    /**
     * @param id 카테고리 인덱스
     * @return not null인 id값
     */
    private BooleanExpression categoryIdEq(Long id){
        return (id != null) ? qArticle.categoryId.eq(id) : null;
    }

    /**
     * @param isDel 삭제 여부
     * @return 삭제되지 않은 내용들
     */
    private BooleanExpression isDelEq(boolean isDel){
        return qArticle.isDel.eq(isDel);
    }

//    private BooleanExpression titleContains(String title){
//        return qArticle.isDel.eq(false);
//    }
//
//    private BooleanExpression contentContains(String content){
//        return qArticle.isDel.eq(false);
//    }
//
//    private BooleanExpression isTopEq(boolean isTop){
//        return qArticle.isDel.eq(false);
//    }
}
