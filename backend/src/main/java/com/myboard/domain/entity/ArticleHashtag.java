package com.myboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "articles_hashtags")
@NoArgsConstructor
@Getter
public class ArticleHashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_hashtag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(nullable = false, name = "hashtag_id")
    private Hashtag hashtag;

    public ArticleHashtag(Article article, Hashtag hashtag) {
        this.article = article;
        this.hashtag = hashtag;
    }
}
