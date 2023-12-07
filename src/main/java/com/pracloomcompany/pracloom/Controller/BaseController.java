package com.pracloomcompany.pracloom.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseController {

    // Global exception handler for all controllers
    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public ResponseEntity<String> handleException(Exception ex, HttpServletRequest request) {
        log.error("Exception occurred in {} {}: {}", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}
