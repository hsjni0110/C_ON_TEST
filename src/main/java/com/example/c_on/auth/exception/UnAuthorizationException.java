package com.example.c_on.auth.exception;

public class UnAuthorizationException extends RuntimeException{

    public UnAuthorizationException(final String message) {
        super(message);
    }
}
