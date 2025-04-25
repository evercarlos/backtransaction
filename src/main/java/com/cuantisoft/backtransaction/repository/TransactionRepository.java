package com.cuantisoft.backtransaction.repository;

import com.cuantisoft.backtransaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
