package com.myboard.repository;

import com.myboard.domain.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT a FROM Article a ORDER BY a.id DESC")
    public Page<Article> findAll(Pageable pageable);
}
