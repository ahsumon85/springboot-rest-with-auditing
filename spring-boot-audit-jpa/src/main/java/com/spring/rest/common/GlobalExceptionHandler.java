/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.rest.common;

import java.nio.file.AccessDeniedException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler { 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(HttpServletRequest req, Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getClass().getName(), e.getMessage(),  req.getRequestURL().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class}) 
    public ResponseEntity<ErrorResponse> handleAuthenticationException(HttpServletRequest req, Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name(), e.getClass().getName(), e.getMessage(),  req.getRequestURL().toString());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<ErrorResponse> handleIllegealArgumentException(HttpServletRequest req, Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(), e.getClass().getName(), e.getMessage(),  req.getRequestURL().toString());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    
   /*    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { RuntimeException .class,ConstraintViolationException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(HttpServletRequest req, Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getClass().getName(), e.getMessage(),  req.getRequestURL().toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }*/
}
