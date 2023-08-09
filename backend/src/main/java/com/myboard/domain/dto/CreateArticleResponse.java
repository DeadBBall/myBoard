package com.myboard.domain.dto;

import com.myboard.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateArticleResponse {

    private Long id;

    public CreateArticleResponse(Article article) {
        this.id = article.getId();
    }
}
