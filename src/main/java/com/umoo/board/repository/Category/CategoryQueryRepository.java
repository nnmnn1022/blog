package com.umoo.board.repository.Category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umoo.board.condition.ArticleSearchCondition;
import com.umoo.board.entity.Article;
import com.umoo.board.entity.QArticle;
import com.umoo.board.entity.QCategory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CategoryQueryRepository {
    private JPAQueryFactory jpaQueryFactory;
    static final QCategory category = new QCategory("category");

    /**
     *
     * @param condition 검색 조건들
     * @return List
     */
    public List<Article> searchByWhere(ArticleSearchCondition condition){
        List<Article> articleList = jpaQueryFactory.selectFrom(category)
                .where(
                        categoryIdEq(condition.getCategoryId()),
                        isDelEq(false)
                )
                .orderBy(
                        category.id.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        /*total Count 쿼리를 직접 날린다.*/
        long total = jpaQueryFactory.selectFrom(category)
                .where(
                        categoryIdEq(condition.getCategoryId()),
                        isDelEq(false)
                )
                .stream().count();

        return new PageImpl<>(articleList, pageable, total);
    }
}
