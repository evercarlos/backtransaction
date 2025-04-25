package com.cuantisoft.backtransaction.model.dto;

import com.cuantisoft.backtransaction.model.enums.TransactionStatus;

import java.math.BigDecimal;

public record TransactionResponseWebDto(
        Integer id,
        String accountExternalIdDebit,
        String accountExternalIdCredit,
        Integer transferTypeId,
        BigDecimal amount,
        TransactionStatus transactionStatus
) {
}
