package com.myboard.domain.dto;

import com.myboard.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    private String writer;
    private LocalDate createdAt;
    private Long commentCount;
    private Long viewCount;
    private Long LikeCount;
    private Boolean isRecent;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.createdAt = article.getCreatedAt();
        this.commentCount = article.getCommentCount();
        this.LikeCount = article.getLikeCount();
        this.viewCount = article.getViewCount();
        this.isRecent = Period.between(createdAt, LocalDate.now()).getDays() <= 3 ? true : false;
    }

}
