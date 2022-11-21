package com.umoo.board.repository;

import com.umoo.board.entity.Article;
import com.umoo.board.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// JpaRepository<엔티티 이름, primaryKey Type>
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByIsDel(boolean isDel);
}
