package com.myboard.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUnlikeRequest {

    @NotBlank(message = "사용자를 확인할 수 없습니다.")
    @Size(max = 36)
    private String guestId;

}
