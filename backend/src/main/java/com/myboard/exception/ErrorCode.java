package com.myboard.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    WRONG_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    ALREADY_LIKED(400, "이미 좋아요를 누른 게시물입니다."),
    NOT_LIKED(400, "좋아요를 누르지 않은 게시물입니다."),

    ARTICLE_NOT_FOUND(404, "게시물이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(404, "댓글이 존재하지 않습니다.");

    private ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;

}
