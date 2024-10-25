package com.argus.cms.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{
    private HttpStatus statusCode;

    public CustomException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
