package com.argus.cms.advice;

import com.argus.cms.dto.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleExpiredJwtException(Exception e) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}   