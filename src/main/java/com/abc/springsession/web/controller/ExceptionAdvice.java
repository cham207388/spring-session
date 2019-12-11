package com.abc.springsession.web.controller;

import com.abc.springsession.exception.MyError;
import com.abc.springsession.exception.LoginError;
import com.abc.springsession.exception.ResourceNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MyError.class)
    public ResponseEntity<String> handleEmpException(MyError e){
        return new ResponseEntity<>(e.getMessage()+" at " +e.getTimestamp(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginError.class)
    public ResponseEntity<String> handleLoginError(LoginError error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundError.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundError error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }
}
