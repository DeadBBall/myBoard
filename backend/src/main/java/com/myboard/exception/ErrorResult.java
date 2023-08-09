package com.myboard.exception;

import lombok.Getter;

@Getter
public class ErrorResult {

    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }
}
