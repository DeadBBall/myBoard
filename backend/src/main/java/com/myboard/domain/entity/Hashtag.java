package com.myboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hashtags")
@NoArgsConstructor
@Getter
public class Hashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(nullable = false, length = 10, unique = true)
    private String name;

    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL)
    private List<ArticleHashtag> articleList;

    @Builder
    public Hashtag(String name) {
        this.name = name;
    }
}
