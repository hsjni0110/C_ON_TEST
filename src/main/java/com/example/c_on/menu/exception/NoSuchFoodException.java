package com.example.c_on.menu.exception;

public class NoSuchFoodException extends RuntimeException{

    public NoSuchFoodException(final String message) {
        super(message);
    }

    public NoSuchFoodException() {
        this("존재하지 않는 음식입니다.");
    }
}
