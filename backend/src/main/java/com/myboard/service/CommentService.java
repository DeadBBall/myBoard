package com.myboard.service;

import com.myboard.domain.dto.CommentListResponse;
import com.myboard.domain.dto.CreateCommentRequest;
import com.myboard.domain.dto.CreateCommentResponse;
import com.myboard.domain.dto.RemoveCommentRequest;
import com.myboard.domain.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface CommentService {

    public CreateCommentResponse addComment(Long articleId, CreateCommentRequest createCommentRequest) throws NoSuchAlgorithmException;
    public CommentListResponse findAllComment(Pageable pageable);
    public void removeComment(Long commentId, RemoveCommentRequest removeCommentRequest) throws NoSuchAlgorithmException;
}
