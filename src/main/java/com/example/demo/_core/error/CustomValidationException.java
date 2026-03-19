package com.example.demo._core.error;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public CustomValidationException(Map<String, String> errors) {
        super("유효성 검사 실패");
        this.errors = errors;
    }
}
