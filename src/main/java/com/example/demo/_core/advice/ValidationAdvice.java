package com.example.demo._core.advice;

import com.example.demo._core.error.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ValidationAdvice {

    @Around("execution(* com.example.demo..*Controller.*(..))")
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();

        for (Object arg : args) {
            if (arg instanceof BindingResult br && br.hasErrors()) {
                Map<String, String> errorMap = new HashMap<>();

                for (FieldError error : br.getFieldErrors()) {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }

                throw new CustomValidationException(errorMap);
            }
        }

        return jp.proceed();
    }
}
