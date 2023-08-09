package com.myboard.controller;

import com.myboard.domain.dto.*;
import com.myboard.service.ArticleService;
import com.myboard.service.CommentService;
import com.myboard.service.LikeService;
import com.myboard.domain.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final ArticleService articleService;
    private final LikeService likeService;
    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<?> articleAdd(@RequestBody @Valid CreateArticleRequest createArticleRequest) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(articleService.addArticle(createArticleRequest), HttpStatus.CREATED);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<?> articleDetails(@PathVariable Long articleId) {
        return new ResponseEntity<>(articleService.findArticle(articleId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> articleList(@PageableDefault(size = 20) Pageable pageable) {
        return new ResponseEntity<>(articleService.findAllArticle(pageable), HttpStatus.OK);
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<?> articleRemove(@PathVariable Long articleId, @RequestBody RemoveArticleRequest removeArticleRequest) throws NoSuchAlgorithmException {
        articleService.removeArticle(articleId, removeArticleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> articleModify(@PathVariable Long articleId, @RequestBody ModifyArticleRequest modifyArticleRequest) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(articleService.modifyArticle(articleId, modifyArticleRequest), HttpStatus.OK);
    }

    @PostMapping("{articleId}/likes")
    public ResponseEntity<?> articleLike(@PathVariable Long articleId, @RequestBody ArticleLikeRequest articleLikeRequest) throws IllegalAccessException {
        return new ResponseEntity<>(likeService.likeArticle(articleId, articleLikeRequest), HttpStatus.OK);
    }

    @DeleteMapping("{articleId}/likes")
    public ResponseEntity<?> articleUnlike(@PathVariable Long articleId, @RequestBody ArticleUnlikeRequest articleUnlikeRequest) throws IllegalAccessException {
        likeService.unlikeArticle(articleId, articleUnlikeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{articleId}/comments")
    public ResponseEntity<?> commentList(@PathVariable Long articleId, @PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(commentService.findAllComment(pageable), HttpStatus.OK);
    }

    @PostMapping("{articleId}/comments")
    public ResponseEntity<?> commentAdd(@PathVariable Long articleId, @RequestBody CreateCommentRequest createCommentRequest) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(commentService.addComment(articleId, createCommentRequest), HttpStatus.OK);
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<?> commentRemove(@PathVariable Long commentId, @RequestBody RemoveCommentRequest removeCommentRequest) throws NoSuchAlgorithmException {
        commentService.removeComment(commentId, removeCommentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
