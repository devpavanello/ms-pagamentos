package com.github.devpavanello.ms_pagamento.controller.handlers;

import com.github.devpavanello.ms_pagamento.dto.CustomErrorDTO;
import com.github.devpavanello.ms_pagamento.dto.ValidationErrorDTO;
import com.github.devpavanello.ms_pagamento.service.exception.DatabaseException;
import com.github.devpavanello.ms_pagamento.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.Instant;

//É uma classe que vai tratar globalmente minha API
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//nossa classe
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException exception,
                                                           HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomErrorDTO errorDTO = new CustomErrorDTO(Instant.now().toString(),
                status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValidation(MethodArgumentNotValidException e,
                                                                      HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO validationError = new ValidationErrorDTO(Instant.now().toString(),
                status.value(), "Dados inválidos", request.getRequestURI());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()){
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<CustomErrorDTO> handlerMethodValidation(HandlerMethodValidationException e,
                                                                  HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now().toString(),
                status.value(), "Falha na validação de dados", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> databaseException(DatabaseException e,
                                                            HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now().toString(),
                status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
