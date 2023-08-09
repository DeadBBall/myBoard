package com.myboard.repository;

import com.myboard.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    public Optional<Like> findByArticleIdAndGuestId(Long articleId, String guestId);
    public Optional<Like> deleteByArticleIdAndGuestId(Long articleId, String guestId);
}
