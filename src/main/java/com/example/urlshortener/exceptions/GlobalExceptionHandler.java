package com.example.urlshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleURLNotFoundException(URLNotFoundException ex){
        URLException urlException = new URLException(
                ex.getMessage(),
                ex,
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(urlException, HttpStatus.NOT_FOUND);
    }
}
