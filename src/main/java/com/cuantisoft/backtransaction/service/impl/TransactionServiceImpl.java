package com.cuantisoft.backtransaction.service.impl;

import com.cuantisoft.backtransaction.config.TransactionNotFoundException;
import com.cuantisoft.backtransaction.exception.TransactionException;
import com.cuantisoft.backtransaction.model.dto.TransactionRequestWebDto;
import com.cuantisoft.backtransaction.model.dto.TransactionResponseWebDto;
import com.cuantisoft.backtransaction.model.entity.Transaction;
import com.cuantisoft.backtransaction.model.enums.TransactionStatus;
import com.cuantisoft.backtransaction.model.mapper.TransactionMapper;
import com.cuantisoft.backtransaction.repository.TransactionRepository;
import com.cuantisoft.backtransaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

//FIXME: Refactorizar errores con enum
@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponseWebDto> findAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper.MAPPER::toTransactionResponseWebDto).toList();
    }

    @Override
    public TransactionResponseWebDto create(TransactionRequestWebDto transactionRequestWebDto) {
        Transaction transaction = TransactionMapper.MAPPER.toTransaction(transactionRequestWebDto);
        transaction.setState(Boolean.TRUE);
        transaction.setTransactionStatus(TransactionStatus.fromName(TransactionStatus.PENDING));
        transactionRepository.save(transaction);
        return TransactionMapper.MAPPER.toTransactionResponseWebDto(transaction);
    }

    @Override
    public TransactionResponseWebDto update(TransactionRequestWebDto request) {
        transactionRepository.findById(request.id())
                .orElseThrow(() -> new TransactionException(HttpStatus.NOT_FOUND, "T400_1", "no se encontro el registro"));

        Transaction transaction = TransactionMapper.MAPPER.toTransaction(request);
        transaction.setState(Boolean.TRUE);

        transactionRepository.save(transaction);
        return TransactionMapper.MAPPER.toTransactionResponseWebDto(transaction);
    }


    @Override
    public TransactionResponseWebDto findById(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionException(HttpStatus.NOT_FOUND, "T400_1", "no se encontro el registro"));
                //.orElseThrow(() -> new TransactionNotFoundException(id));
        return TransactionMapper.MAPPER.toTransactionResponseWebDto(transaction);
    }

    @Override
    public TransactionResponseWebDto delete(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionException(HttpStatus.NOT_FOUND, "T400_1", "no se encontro el registro"));

        transaction.setState(Boolean.FALSE);
        transactionRepository.save(transaction);
        return TransactionMapper.MAPPER.toTransactionResponseWebDto(transaction);
    }
}
