package com.eteration.simplebanking.exception;

import com.eteration.simplebanking.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchElementFoundException(AccountNotFoundException accountNotFoundException, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>(accountNotFoundException));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> handleDuplicateUserException(InsufficientBalanceException insufficientBalanceException, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>(insufficientBalanceException));
    }
}
