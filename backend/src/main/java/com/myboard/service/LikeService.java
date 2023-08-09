package com.myboard.service;

import com.myboard.domain.dto.ArticleLikeRequest;
import com.myboard.domain.dto.ArticleLikeResponse;
import com.myboard.domain.dto.ArticleUnlikeRequest;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public ArticleLikeResponse likeArticle(Long id, ArticleLikeRequest articleLikeRequest) throws IllegalAccessException;
    public void unlikeArticle(Long id, ArticleUnlikeRequest articleUnlikeRequest) throws IllegalAccessException;
}
