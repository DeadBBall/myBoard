package com.myboard.domain.dto;

import com.myboard.domain.entity.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyArticleRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 30, message = "제목은 최대 30글자까지 가능합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Size(max = 5, message = "해시태그는 최대 5개까지 가능합니다.")
    private List<@NotBlank(message = "해시태그를 입력해주세요.") @Size(max = 10, message = "해시태그는 최대 10글자까지 가능합니다.") String> hashtagList;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String editDeletePassword;

    public Article toEntity() {
        return Article
                .builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
