package com.myboard.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveCommentRequest {

    @NotBlank(message = "비밀먼호를 입력해주세요.")
    private String deletePassword;
}
