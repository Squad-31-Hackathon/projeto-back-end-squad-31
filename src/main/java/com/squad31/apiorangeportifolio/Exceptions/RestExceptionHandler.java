package com.squad31.apiorangeportifolio.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

@ExceptionHandler(value = BadRequestException.class)
    private ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex) {

    ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
}


    @ExceptionHandler(value = NotFoundException.class)
    private ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
