package com.squad31.apiorangeportifolio.Exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(value = ProjectNotFoundException.class)
    private ResponseEntity<ErrorDTO> handleProjectNotFoundException(ProjectNotFoundException ex) {
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    private ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException ex){
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = ImageProcessingException.class)
    private ResponseEntity<ErrorDTO> handleImageProcessingException(ImageProcessingException ex){
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    private ResponseEntity<ErrorDTO> handleValidationException(ConstraintViolationException ex){
        log.error(ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    private ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex){
        log.error("Credenciais incorretas - {}", ex.getMessage());
        ErrorDTO errorResponse = new ErrorDTO("Credenciais incorretas");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleSecurityException(Exception ex) {
        ex.printStackTrace(); // verificar o log pois Exception é muito generalizado
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
