package com.cuantisoft.backtransaction.exception;

// The permitted ones must belong to the same package.
// It was implemented by knowledge
public sealed interface OperationResult permits TransactionSuccess, TransactionError {
}

