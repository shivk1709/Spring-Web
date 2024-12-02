package com.project.java.exception;

import com.project.java.dto.Payload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Payload> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Payload payload = Payload.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }

}
