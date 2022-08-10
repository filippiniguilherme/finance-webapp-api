package com.financewebapp.api.controllers.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    public ResponseEntity<String> errorNotFound(Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<String> errorBadRequest(Exception e) {
        return ResponseEntity.badRequest().build();
    }
}
