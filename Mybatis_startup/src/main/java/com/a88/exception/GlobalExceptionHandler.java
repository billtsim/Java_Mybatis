package com.a88.exception;

import com.a88.Pojo.result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public result ex(Exception ex) {
        ex.printStackTrace();
        return result.error("sorry, action is failed, please contact our staff");
    }
}