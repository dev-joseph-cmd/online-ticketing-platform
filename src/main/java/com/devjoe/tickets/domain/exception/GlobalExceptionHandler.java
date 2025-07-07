package com.devjoe.tickets.domain.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentException(MethodArgumentNotValidException exception){


        List<FieldError> fieldErrors = exception.getFieldErrors();
        String errorMessage = fieldErrors
                .stream().findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("field error validation");

        Map<String, Object> response = new HashMap<>();
        response.put("error", errorMessage);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException exception){
        log.error(" Caught exception ", exception);
        Map<String, Object> exceptionMessage = Map.of("user not found", exception.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }
}
