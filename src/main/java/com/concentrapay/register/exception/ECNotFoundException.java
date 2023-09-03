package com.concentrapay.register.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ECNotFoundException extends RuntimeException {
    public ECNotFoundException(String message) {
        super(message);
    }
}