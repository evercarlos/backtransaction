package com.cuantisoft.backtransaction.model.dto;

import com.cuantisoft.backtransaction.model.enums.TransferType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionRequestWebDto(
        Integer id,

        @NotNull(message = "El campo accountExternalIdDebit no puede ser nulo")
        Integer accountExternalIdDebit,

        @NotNull(message = "El campo accountExternalIdCredit no puede ser nulo")
        Integer accountExternalIdCredit,

        @NotNull(message = "El campo transferType no puede ser nulo")
        TransferType transferType,  // Usamos el enum aquí

        @NotNull(message = "El campo amount no puede ser nulo")
        @DecimalMin(value = "0.01", message = "El amount debe ser mayor a 0")
        BigDecimal amount
) {
}
