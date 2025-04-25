package com.cuantisoft.backtransaction.exception;

// It was implemented by knowledge
public final class TransactionError implements OperationResult {

    private final String code;
    private final String message;

    public TransactionError(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}