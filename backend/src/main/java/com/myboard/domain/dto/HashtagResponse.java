package com.myboard.domain.dto;

import com.myboard.domain.entity.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HashtagResponse {

    private Long id;

    private String name;

    public HashtagResponse(Hashtag hashtag) {
        this.id = hashtag.getId();
        this.name = hashtag.getName();
    }
}
