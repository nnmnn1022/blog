package com.umoo.board.specification;

import com.umoo.board.entity.Article;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 이후 검색 기능 구현 시에 보충해야 함
 */
public class ArticleSpecs {

    /**
     * enum(열거형)으로 어떤 검색을 할지 선택하도록 함
     */
    public enum SearchKey {
        TITLE("title"),
        CONTENT("content"),
        CATEGORY("category"),
        ISDEL("isDel"),
        ISTOP("isTop"),
        REGDATE("regDate"),
        MODDATE("modDate");

        private final String value;

        SearchKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static Specification<Article> searchWith(Map<SearchKey, Object> searchKeyword) {
        return (Specification<Article>) ((root, query, builder) -> {
            /**
             * import javax.persistence.criteria.CriteriaBuilder;
             * import javax.persistence.criteria.Predicate;
             * import javax.persistence.criteria.Root;
             * 해당 람다식은 Specification의 toPredicate() 메소드를 구현한 것
             * getPredicateWithKeyword 메소드의 조건을 모두 만족하는 내용만 반환 (AND)
             */
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<SearchKey, Object> searchKeyword, Root<Article> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (SearchKey key : searchKeyword.keySet()) {
            switch (key) {
                case TITLE:
                case CONTENT:
                    predicate.add(builder.like(
                            root.get(key.value), ("%" + key.value + "%")
                    ));
                    break;
                case CATEGORY:
                case ISDEL:
                case ISTOP:
                    predicate.add(builder.equal(
                            root.get(key.value), searchKeyword.get(key.value)
                    ));
                    break;
                case REGDATE:
                case MODDATE:
                    break;

            }
        }
        return predicate;
    }
}
