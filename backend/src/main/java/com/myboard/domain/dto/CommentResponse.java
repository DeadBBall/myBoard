package com.myboard.domain.dto;

import com.myboard.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;

    private String writer;

    private String content;

    private LocalDate createdAt;

    private Boolean isDeleted;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.isDeleted = comment.getIsDeleted();
    }
}
