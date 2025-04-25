package com.cuantisoft.backtransaction.model.enums;

import java.util.Arrays;

public enum TransactionStatus {
    PENDING("pendiente"),
    APPROVED("aprovado"),
    REJECTED("rechazado");

    private String label;

    TransactionStatus(String label) {
        this.label = label;
    }

    public static String fromName(TransactionStatus transactionStatus) {
        if (transactionStatus == null) {
            throw new IllegalArgumentException("El estado de transacción no puede ser nulo.");
        }
        return transactionStatus.label;
    }

    public static TransactionStatus fromLabel(String label) {
        return Arrays.stream(TransactionStatus.values())
                .filter(status -> status.label.equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe un estado de transacción con el label: " + label));
    }
}
