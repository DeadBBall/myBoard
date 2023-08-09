package com.myboard.repository;

import com.myboard.domain.entity.ArticleHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long> {
    public Optional<ArticleHashtag> findByArticleIdAndHashtagId(Long ArticleId, Integer HashtagId);
}
