package com.argus.cms.advice;

import com.argus.cms.dto.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleMalformedJwtException(MalformedJwtException e) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponseDTO> handleSignatureException(SignatureException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.FORBIDDEN, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentialsException(BadCredentialsException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleExpiredJwtException(ExpiredJwtException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.FORBIDDEN, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}