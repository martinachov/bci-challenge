package com.martinachov.bci.challenge.infrastructure.error;

import com.martinachov.bci.challenge.application.exception.UserAlreadyExistException;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ErrorControllerAdvise extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvise.class);

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> userAlreadyExistHandler(UserAlreadyExistException ex, WebRequest request){
        logger.error(ex.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ErrorInfoDTO error = ErrorInfoDTO.builder()
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, error, headers, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException ex, WebRequest request){
        logger.error(ex.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ErrorInfoDTO error = ErrorInfoDTO.builder()
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleBaseException(SignatureException ex, WebRequest request) {
        logger.error(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ErrorInfoDTO error = ErrorInfoDTO.builder()
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, error, headers, HttpStatus.UNAUTHORIZED, request);
    }
}
