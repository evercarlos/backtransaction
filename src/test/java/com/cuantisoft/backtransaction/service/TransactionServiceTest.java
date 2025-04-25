package com.cuantisoft.backtransaction.service;

import com.cuantisoft.backtransaction.exception.TransactionException;
import com.cuantisoft.backtransaction.model.dto.TransactionRequestWebDto;
import com.cuantisoft.backtransaction.model.dto.TransactionResponseWebDto;
import com.cuantisoft.backtransaction.model.entity.Transaction;
import com.cuantisoft.backtransaction.model.enums.TransferType;
import com.cuantisoft.backtransaction.repository.TransactionRepository;
import com.cuantisoft.backtransaction.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

// ermite a Mockito trabajar de manera integrada con JUnit 5
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    //  Mockito inyectará los mocks (si hay inyeccion, las inyecta automaticamente)
    @InjectMocks
    private TransactionServiceImpl transactionService;


    @BeforeEach
    void setUp() {
        // Cualquier inicialización antes de cada prueba
    }

    @Test
    void shouldCreateTransactionSuccessfully() {
        // Arrange
        TransactionRequestWebDto request = new TransactionRequestWebDto(
                0,
                1,
                2,
                TransferType.CREDIT_CARD,
                new BigDecimal("100.00")
        );
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        // Simula la respuesta del repositorio
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        TransactionResponseWebDto response = transactionService.create(request);

        // Assert
        Mockito.verify(transactionRepository).save(captor.capture());
        Transaction saved = captor.getValue();
        Assertions.assertEquals(request.accountExternalIdDebit(), saved.getAccountExternalIdDebit());
        Assertions.assertEquals(request.accountExternalIdCredit(), saved.getAccountExternalIdCredit());
        Assertions.assertEquals(Integer.valueOf(request.accountExternalIdCredit()), Integer.valueOf(response.accountExternalIdCredit()));
    }

    @Test
    void shouldReturnTransactionById() {
        // Arrange
        int id = 1;
        Transaction entity = new Transaction();
        entity.setId(id);
        entity.setAccountExternalIdDebit(1);
        entity.setAccountExternalIdCredit(2);
        entity.setTransferType(TransferType.CREDIT_CARD.toString());
        entity.setAmount(new BigDecimal("200"));
        entity.setTransactionStatus("pendiente");
        entity.setState(Boolean.TRUE);

        Mockito.when(transactionRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        TransactionResponseWebDto response = transactionService.findById(id);

        // Assert
        Assertions.assertEquals("1", String.valueOf(response.accountExternalIdDebit()));
    }

    @Test
    void shouldThrowWhenTransactionNotFound() {
        // Arrange
        Integer id = 1;
        Mockito.when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        // Assert + Act
        Assertions.assertThrows(TransactionException.class, () -> transactionService.findById(id));
    }
}
