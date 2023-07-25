package com.example.crudp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
public ResponseEntity<ErrorDetail> handlerResponseNotFound(ResourceNotFound exception, WebRequest webRequest ){
    ErrorDetail errorDetails = new ErrorDetail(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "User_not_found"

    );

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorDetail> handlerEmailAlreadyExist(EmailException exception, WebRequest webRequest ){
        ErrorDetail errorDetails = new ErrorDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_ALREADY_EXIST"

        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handlerGlobalException(Exception exception, WebRequest webRequest ){
        ErrorDetail errorDetails = new ErrorDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"

        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EmailException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors= new HashMap<>();
        List<ObjectError> errorlist =ex.getBindingResult().getAllErrors();
        errorlist.forEach((error) ->{
            String fieldName = ((FieldError) error ).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);

        });
        return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST );

    }


    }


