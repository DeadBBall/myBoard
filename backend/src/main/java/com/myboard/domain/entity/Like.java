package com.myboard.domain.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@Getter
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column(nullable = false, name = "guest_id", length = 36)
    private String guestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "article_id")
    private Article article;

    @Builder
    public Like(String guestId, Article article) {
        this.guestId = guestId;
        this.article = article;
    }
}
