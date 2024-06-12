package com.example.demo.config;

import com.example.demo.dto.response.ErrorResponseDto;
import com.example.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setHttStatusCode(400);
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setHttStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleHibernateValidationException(MethodArgumentNotValidException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setHttStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }
}
