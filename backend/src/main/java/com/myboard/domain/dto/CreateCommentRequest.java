package com.myboard.domain.dto;

import com.myboard.domain.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 100, message = "내용은 최대 100글자까지 가능합니다.")
    private String content;

    @NotBlank(message = "작성자를 입력해주세요.")
    @Size(max = 10, message = "작성자는 최대 10글자까지 가능합니다.")
    private String writer;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String deletePassword;

    public Comment toEntity() {
        return Comment
                .builder()
                .content(this.getContent())
                .writer(this.getWriter())
                .build();
    }
}
