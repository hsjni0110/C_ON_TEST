package com.example.c_on.customer.exception;

public class NoSuchCustomerException extends RuntimeException {

    public NoSuchCustomerException(final String message) {
        super(message);
    }

    public NoSuchCustomerException() {
        this("존재하지 않는 회원입니다.");
    }
}
