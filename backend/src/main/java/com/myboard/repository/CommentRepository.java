package com.myboard.repository;

import com.myboard.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByArticleId(Long articleId);
    @Query(value = "SELECT c FROM Comment c ORDER BY c.id DESC")
    public Page<Comment> findAll(Pageable pageable);

    public Long countAllByIsDeletedFalse();
}
