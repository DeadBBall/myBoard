package com.myboard.service;


import com.myboard.domain.dto.*;
import com.myboard.domain.entity.Article;
import com.myboard.domain.entity.ArticleHashtag;
import com.myboard.domain.entity.Comment;
import com.myboard.domain.entity.Hashtag;
import com.myboard.repository.ArticleHashtagRepository;
import com.myboard.repository.ArticleRepository;
import com.myboard.repository.CommentRepository;
import com.myboard.repository.HashtagRepository;
import com.myboard.util.HashUtil;
import com.myboard.domain.dto.*;
import com.myboard.domain.entity.*;
import com.myboard.exception.CustomException;
import com.myboard.exception.ErrorCode;
import com.myboard.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleServiceImpl implements  ArticleService{
    private final ArticleRepository articleRepository;
    private final HashtagRepository hashtagRepository;
    private final ArticleHashtagRepository articleHashtagRepository;
    private final CommentRepository commentRepository;

    @Override
    public CreateArticleResponse addArticle(CreateArticleRequest createArticleRequest){
        Article article = createArticleRequest.toEntity();
        article.hashEditDeletePassword(HashUtil.hashWithSHA256(createArticleRequest.getEditDeletePassword()));
        articleRepository.save(article);
        createArticleRequest.getHashtagList().stream()
                .map(name -> getOrCreateHashtag(name, article))
                .collect(Collectors.toList());
        return new CreateArticleResponse(article);
    }

    public ArticleDetailResponse findArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));
        article.increaseViewCount();
        article = articleRepository.save(article);
        ArticleDetailResponse articleDetailResponse = new ArticleDetailResponse(article);
        List<Comment> commentList = commentRepository.findAllByArticleId(article.getId());
        List<CommentResponse> commentResponseList = commentList.stream()
                .map(comment -> new CommentResponse(comment))
                .collect(Collectors.toList());
        List<HashtagResponse> hashtagResponseList = article.getHashtagSet().stream()
                .map(articleHashtag -> new HashtagResponse(articleHashtag.getHashtag()))
                .collect(Collectors.toList());
        articleDetailResponse.updateHashtagList(hashtagResponseList);
        articleDetailResponse.updateCommentList(commentResponseList);
        return articleDetailResponse;
    }

    @Override
    public ArticleListResponse findAllArticle(Pageable pageable) {
        Page<Article> articleList = articleRepository.findAll(pageable);
        List<ArticleResponse> articleResponseList = articleList.stream()
                .map(article -> new ArticleResponse(article))
                .collect(Collectors.toList());
        Long totalArticleCount = articleList.getTotalElements();
        Long totalCommentCount = commentRepository.countAllByIsDeletedFalse();
        return ArticleListResponse.builder()
                .articleResponseList(articleResponseList)
                .totalArticleCount(totalArticleCount)
                .totalCommentCount(totalCommentCount)
                .build();
    }

    @Override
    public ModifyArticleResponse modifyArticle(Long id, ModifyArticleRequest modifyArticleRequest){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));
        String originalPassword = article.getEditDeletePassword();
        String inputPassword = modifyArticleRequest.getEditDeletePassword();
        if(!HashUtil.comparePasswords(originalPassword, inputPassword))
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        article.getHashtagSet().clear();
        article.updateTitle(modifyArticleRequest.getTitle());
        article.updateContent(modifyArticleRequest.getContent());
        articleRepository.save(article);
        modifyArticleRequest.getHashtagList().stream()
                .forEach(name -> getOrCreateHashtag(name, article));
        return new ModifyArticleResponse(article);
    }

    public void removeArticle(Long id, RemoveArticleRequest removeArticleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));
        String originalPassword = article.getEditDeletePassword();
        String inputPassword = removeArticleRequest.getEditDeletePassword();
        if(!HashUtil.comparePasswords(originalPassword, inputPassword))
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        articleRepository.delete(article);
    }

    private HashtagResponse getOrCreateHashtag(String name, Article article) {
        Hashtag hashtag = hashtagRepository.findByName(name)
                .orElseGet(() -> Hashtag.builder()
                        .name(name)
                        .build());
        if(hashtag.getId() == null) hashtag = hashtagRepository.save(hashtag);
        ArticleHashtag articleHashtag = new ArticleHashtag(article, hashtag);
        articleHashtagRepository.save(articleHashtag);
        return new HashtagResponse(hashtag);
    }
}
