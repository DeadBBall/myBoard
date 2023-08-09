package com.myboard.service;

import com.myboard.domain.dto.*;
import com.myboard.domain.entity.Article;
import com.myboard.domain.entity.Comment;
import com.myboard.util.HashUtil;
import com.myboard.domain.dto.*;
import com.myboard.exception.CustomException;
import com.myboard.exception.ErrorCode;
import com.myboard.repository.ArticleRepository;
import com.myboard.repository.CommentRepository;
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
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Override
    public CreateCommentResponse addComment(Long articleId, CreateCommentRequest createCommentRequest){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        Comment comment = createCommentRequest.toEntity();
        comment.hashEditDeletePassword(HashUtil.hashWithSHA256(createCommentRequest.getDeletePassword()));
        comment.updateArticle(article);
        comment = commentRepository.save(comment);
        return new CreateCommentResponse(comment.getId());
    }

    @Override
    public CommentListResponse findAllComment(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        List<CommentResponse> commentResponseList = comments.stream()
                .map(comment -> new CommentResponse(comment))
                .collect(Collectors.toList());
        return CommentListResponse.builder()
                .commentResponseList(commentResponseList)
                .totalCount(commentRepository.countAllByIsDeletedFalse())
                .totalPage(comments.getTotalPages())
                .build();
    }

    @Override
    public void removeComment(Long commentId, RemoveCommentRequest removeCommentRequest){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        String originalPassword = comment.getDeletePassword();
        String inputPassword = removeCommentRequest.getDeletePassword();
        if(!HashUtil.comparePasswords(originalPassword, inputPassword))
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        commentRepository.delete(comment);
    }

}
