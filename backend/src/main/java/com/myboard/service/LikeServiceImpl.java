package com.myboard.service;

import com.myboard.domain.dto.ArticleLikeRequest;
import com.myboard.domain.dto.ArticleLikeResponse;
import com.myboard.domain.dto.ArticleUnlikeRequest;
import com.myboard.domain.entity.Article;
import com.myboard.domain.entity.Like;
import com.myboard.repository.LikeRepository;
import com.myboard.exception.CustomException;
import com.myboard.exception.ErrorCode;
import com.myboard.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService{

    private final ArticleRepository articleRepository;
    private final LikeRepository likeRepository;

    @Override
    public ArticleLikeResponse likeArticle(Long id, ArticleLikeRequest articleLikeRequest) throws IllegalAccessException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));
        if(likeRepository.findByArticleIdAndGuestId(id, articleLikeRequest.getGuestId()).isPresent())
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        Like like = Like.builder()
                .article(article)
                .guestId(articleLikeRequest.getGuestId())
                .build();
        like = likeRepository.save(like);
        return new ArticleLikeResponse(like.getId());
    }

    @Override
    public void unlikeArticle(Long id, ArticleUnlikeRequest articleUnlikeRequest) throws IllegalAccessException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));
        if(likeRepository.findByArticleIdAndGuestId(id, articleUnlikeRequest.getGuestId()).isEmpty())
            throw new CustomException(ErrorCode.NOT_LIKED);
        likeRepository.deleteByArticleIdAndGuestId(id, articleUnlikeRequest.getGuestId());
    }
}
