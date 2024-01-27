package com.squad31.apiorangeportifolio.Exceptions;

import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    private ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex) {
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(value = NotFoundException.class)
    private ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleSecurityException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        ResponseEntity<ErrorDTO> response = null;

        if (ex instanceof BadCredentialsException) {

            ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());
            response = ResponseEntity.status(401).body(errorResponse);

        }

        if (ex instanceof AccessDeniedException) {

            ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());
            response = ResponseEntity.status(403).body(errorResponse);

        }

        if (ex instanceof InternalAuthenticationServiceException) {

            ErrorDTO errorResponse = new ErrorDTO("Usuário inexistente ou senha inválida");
            response = ResponseEntity.status(401).body(errorResponse);
        }

        return response;
    }


}
