package com.myboard.domain.dto;

import com.myboard.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDetailResponse {
    private Long id;

    private String title;

    private String writer;

    private String content;

    private List<HashtagResponse> hashtagList;

    private List<CommentResponse> commentList;

    private Long likeCount;

    private Long viewCount;

    private LocalDate createdAt;

    public ArticleDetailResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.likeCount = article.getLikeCount();
        this.viewCount = article.getViewCount();
        this.createdAt = article.getCreatedAt();
    }
    public void updateHashtagList(List<HashtagResponse> hashtagList) {
        this.hashtagList = hashtagList;
    }

    public void updateCommentList(List<CommentResponse> commentList) {this.commentList = commentList;}

}
