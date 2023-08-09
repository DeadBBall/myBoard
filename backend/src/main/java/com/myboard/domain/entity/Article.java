package com.myboard.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
@NoArgsConstructor
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE articles SET is_deleted = TRUE WHERE article_id = ?")
@Where(clause = "is_deleted = FALSE")
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 10)
    private String writer;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @Column(nullable = false, name = "edit_delete_password", length = 64)
    private String editDeletePassword;

    @Column(nullable = false, name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Formula("(SELECT count(1) FROM likes l WHERE l.article_id = article_id)")
    private Long likeCount;

    @Formula(("(SELECT count(1) FROM comments c WHERE c.article_id = article_id AND c.is_deleted = FALSE)"))
    private Long commentCount;

    @Column(nullable = false, name = "view_count")
    @ColumnDefault("0")
    private Long viewCount;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ArticleHashtag> hashtagSet = new HashSet<>();

    @OneToMany(mappedBy = "article")
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Article(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void hashEditDeletePassword(String hashedEditDeletePassword) {
        this.editDeletePassword = hashedEditDeletePassword;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
