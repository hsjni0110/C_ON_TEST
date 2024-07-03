package com.example.c_on.order.exception;

public class EmptyTargetException extends RuntimeException{

    public EmptyTargetException(final String message) {
        super(message);
    }
    public EmptyTargetException() {
        this("대상이 비었습니다.");
    }
}
