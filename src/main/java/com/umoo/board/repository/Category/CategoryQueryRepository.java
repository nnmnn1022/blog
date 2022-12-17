package com.umoo.board.repository.Category;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umoo.board.condition.ArticleSearchCondition;
import com.umoo.board.condition.CategorySearchCondition;
import com.umoo.board.entity.Article;
import com.umoo.board.entity.Category;
import com.umoo.board.entity.QArticle;
import com.umoo.board.entity.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    static final QCategory category = new QCategory("category");

    /**
     *
     * @param condition 검색 조건들
     * @return List
     * 삭제 처리 되지 않았으면서, 노출 처리를 확인해서 보여주는 게 필요
     *
     */
    public List<Category> searchByWhere(CategorySearchCondition condition){
        List<Category> categoryList = jpaQueryFactory.select(category)
                .from(category)
                .where(
                        isDelEq(false),
                        isViewEq(true)

                )
                .fetch();

        return categoryList;
    }

    /**
     * @param isDel 삭제 여부
     * @return 삭제되지 않은 내용들
     */
    private BooleanExpression isDelEq(boolean isDel){
        return category.isDel.eq(isDel);
    }

    /**
     * @param isView 노출 여부
     * @return 삭제되지는 않았으나 카테고리에 노출하고 싶지 않은 내용들
     */
    private BooleanExpression isViewEq(boolean isView){
        return category.isView.eq(isView);
    }

//    private BooleanExpression parentIdNull(){
//        return category.parentId.isNull();
//    }
}
