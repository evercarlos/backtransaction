package com.cuantisoft.backtransaction.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TransactionException extends RuntimeException {

    public HttpStatus status;
    public String code;

    public TransactionException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.code = code;
        this.status = httpStatus;
    }
}
