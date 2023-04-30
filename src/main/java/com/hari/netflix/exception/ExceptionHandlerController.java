package com.hari.netflix.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleUncaughtException(RuntimeException exception, HttpServletRequest request) {
        String message = "Unknown error occured";
        System.out.println(exception.getStackTrace());
        return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, message, exception));
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleAuthException(RuntimeException exception, HttpServletRequest request) {
        String message = "Invalid authentication token";
        System.out.println(exception.getStackTrace());
        return buildResponseEntity(new APIError(HttpStatus.UNAUTHORIZED, message, exception));
    }

    @ExceptionHandler({IllegalArgumentException.class, FileNotFoundException.class, ResourceNotFoundException.class, NoSuchFileException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(Exception exception, HttpServletRequest request) {
        String message = "Requested resource could not be located";
        return buildResponseEntity(new APIError(HttpStatus.NOT_FOUND, message, exception));
    }

    private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
