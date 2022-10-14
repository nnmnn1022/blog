package com.umoo.board.repository;

import com.umoo.board.entity.Article;
import com.umoo.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<엔티티 이름, primaryKey Type>
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
