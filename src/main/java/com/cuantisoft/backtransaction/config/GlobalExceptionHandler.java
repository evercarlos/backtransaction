package com.cuantisoft.backtransaction.config;

import com.cuantisoft.backtransaction.exception.TransactionException;
import com.cuantisoft.backtransaction.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleNotFound(TransactionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }*/

    /**
     * Método para controllar errores con HttpStatus
     */
    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(TransactionException ex) {
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
