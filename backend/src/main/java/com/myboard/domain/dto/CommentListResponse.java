package com.myboard.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListResponse {

    private List<CommentResponse> commentResponseList = new ArrayList<>();

    private Integer totalPage;

    private Long totalCount;

}
