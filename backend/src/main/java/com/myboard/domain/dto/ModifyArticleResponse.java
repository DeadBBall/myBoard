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
public class ModifyArticleResponse {

    private Long id;

    public ModifyArticleResponse(Article article) {
        this.id = article.getId();
    }
}
