package com.myretail.productapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionAdvice {

    private Logger log = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exception) {
        log.warn("Invalid request parameters", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<String> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.warn("Invalid request parameter type", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    ResponseEntity<String> handleServerException(HttpServerErrorException exception) {
        log.error("Server error occurred", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error occurred");
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(Exception exception) {
        log.error("Unhandled exception occurred", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred");
    }
}
