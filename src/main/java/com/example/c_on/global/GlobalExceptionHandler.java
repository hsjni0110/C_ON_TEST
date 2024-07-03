package com.example.c_on.global;

import com.example.c_on.auth.exception.UnAuthorizationException;
import com.example.c_on.customer.exception.NoSuchCustomerException;
import com.example.c_on.menu.exception.NoSuchCategoryException;
import com.example.c_on.order.exception.EmptyTargetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchCustomerException.class, NoSuchCategoryException.class})
    public ResponseEntity<ErrorResponse> handleNoSuchData(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UnAuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(EmptyTargetException.class)
    public ResponseEntity<ErrorResponse> handleEmptyTargetException(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
