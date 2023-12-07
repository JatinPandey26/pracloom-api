package com.pracloomcompany.pracloom.Controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleBindException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("error: " + ex.getMessage());
    }
}
