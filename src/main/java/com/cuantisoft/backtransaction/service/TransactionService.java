package com.cuantisoft.backtransaction.service;

import com.cuantisoft.backtransaction.model.dto.TransactionRequestWebDto;
import com.cuantisoft.backtransaction.model.dto.TransactionResponseWebDto;

import java.util.List;

public interface TransactionService {

    public List<TransactionResponseWebDto> findAll();

    public TransactionResponseWebDto create(TransactionRequestWebDto request);

    public TransactionResponseWebDto update(TransactionRequestWebDto request);

    public TransactionResponseWebDto findById(Integer id);

    public TransactionResponseWebDto delete(Integer request);
}
