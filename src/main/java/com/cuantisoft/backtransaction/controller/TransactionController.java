package com.cuantisoft.backtransaction.controller;

import com.cuantisoft.backtransaction.model.dto.TransactionRequestWebDto;
import com.cuantisoft.backtransaction.model.dto.TransactionResponseWebDto;
import com.cuantisoft.backtransaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transaction", produces = "application/json")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionResponseWebDto> findAll() {
        return transactionService.findAll();
    }

    @PostMapping
    public TransactionResponseWebDto create( @Valid @RequestBody TransactionRequestWebDto transactionRequestWebDto) {
        return transactionService.create(transactionRequestWebDto);
    }

    @PutMapping
    public TransactionResponseWebDto update(@RequestBody TransactionRequestWebDto transactionResponseWebDto) {
        return transactionService.update(transactionResponseWebDto);
    }

    @GetMapping("/{id}")
    public TransactionResponseWebDto findById(@RequestBody @PathVariable(value = "id") Integer id) {
        return transactionService.findById(id);
    }


    @DeleteMapping("/{id}")
    public TransactionResponseWebDto delete(@RequestBody @PathVariable(value = "id") Integer id) {
        return transactionService.delete(id);
    }

}








