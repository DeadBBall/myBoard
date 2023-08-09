package com.myboard.service;

import com.myboard.domain.dto.*;
import com.myboard.domain.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {
    public CreateArticleResponse addArticle(CreateArticleRequest createArticleRequest);
    public ArticleDetailResponse findArticle(Long id);
    public ArticleListResponse findAllArticle(Pageable pageable);
    public ModifyArticleResponse modifyArticle(Long id, ModifyArticleRequest modifyArticleRequest);
    public void removeArticle(Long id, RemoveArticleRequest removeArticleRequest);
}
