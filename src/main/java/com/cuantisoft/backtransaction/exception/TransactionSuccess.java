package com.cuantisoft.backtransaction.exception;

// It was implemented by knowledge
public final class TransactionSuccess implements OperationResult {

    private final Object data;

    public TransactionSuccess(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}