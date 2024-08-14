package com.example.urlshortener.exceptions;

import org.springframework.http.HttpStatus;

public class URLException {
    private String message;
    private Throwable cause;
    private HttpStatus httpStatus;

    public URLException(String message, Throwable cause, HttpStatus httpStatus) {
        this.message = message;
        this.cause = cause;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
