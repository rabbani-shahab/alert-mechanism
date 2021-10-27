package com.decathlon.alerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> duplicateRecordException(DuplicateRecordException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badExceptionHandler(BadRequest ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        //ValidationErrorResponse validationErrorResponse =new ValidationErrorResponse(new Date(), ex.getMessage(), request.getDescription(false))
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" " + violation.getMessage()));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = "";
        if (!fieldErrors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            fieldErrors.forEach(fieldError -> builder.append(" " + fieldError.getDefaultMessage()));
            errorMessage = builder.toString();
        } else {
            errorMessage = "MethodArgumentNotValidException occured.";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExternalAPIException.class)
    public ResponseEntity<?> handleExternalAPIException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}