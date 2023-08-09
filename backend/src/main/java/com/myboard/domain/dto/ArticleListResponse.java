package com.myboard.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleListResponse {
    private List<ArticleResponse> articleResponseList = new ArrayList<>();
    private Long totalArticleCount;
    private Long totalCommentCount;
}
