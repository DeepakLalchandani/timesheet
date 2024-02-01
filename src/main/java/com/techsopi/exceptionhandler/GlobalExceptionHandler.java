package com.techsopi.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.techsopi.exception.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleContentNotFoundException(ClientNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<Object> handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Object> handleInvalidFieldException(InvalidFieldException ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> objectNotFoundException(ObjectNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);


    }
}
