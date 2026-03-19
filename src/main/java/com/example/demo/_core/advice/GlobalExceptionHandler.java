package com.example.demo._core.advice;

import com.example.demo._core.error.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public @ResponseBody String onValidationException(CustomValidationException e) {
        String errorMsg = e.getErrors().entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.joining("\\n"));

        return "<script>" +
                "alert('" + errorMsg + "');" +
                "history.back();" +
                "</script>";
    }
}
