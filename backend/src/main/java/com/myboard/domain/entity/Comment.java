package com.myboard.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE comments SET is_deleted = TRUE where comment_id = ?")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "article_id")
    private Article article;

    @Column(length = 100)
    private String content;

    @CreatedDate
    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @Column(nullable = false, name = "delete_password", length = 64)
    private String deletePassword;

    @Column(nullable = false, name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Builder
    public Comment(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public void hashEditDeletePassword(String hashedDeletePassword) {
        this.deletePassword = hashedDeletePassword;
    }

    public void updateArticle(Article article) {
        this.article = article;
    }
}
