package com.cuantisoft.backtransaction.model.mapper;


import com.cuantisoft.backtransaction.model.dto.TransactionRequestWebDto;
import com.cuantisoft.backtransaction.model.dto.TransactionResponseWebDto;
import com.cuantisoft.backtransaction.model.entity.Transaction;
import com.cuantisoft.backtransaction.model.enums.TransactionStatus;
import com.cuantisoft.backtransaction.model.enums.TransferType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "transferType", source = "transferType", qualifiedByName = "transferTypeMapper")
    @Mapping(target = "transactionStatus", ignore = true)
    Transaction toTransaction(TransactionRequestWebDto dto);

    @Mapping(target = "transactionStatus", source = "transactionStatus", qualifiedByName = "transactionStatusMapper")
    TransactionResponseWebDto toTransactionResponseWebDto(Transaction transaction);


    @Named("transactionStatusMapper")
    default TransactionStatus transactionStatusMapper(String transactionStatus) {
        return TransactionStatus.fromLabel(transactionStatus);
    }

    @Named("transferTypeMapper")
    default String transferTypeMapper(TransferType transferType) {
        return transferType.name();
    }
}
