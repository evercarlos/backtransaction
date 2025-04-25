package com.cuantisoft.backtransaction.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_external_id_debit")
    private Integer accountExternalIdDebit;
    @Column(name = "account_external_id_credit")
    private Integer accountExternalIdCredit;
    private String transferType;
    private String transactionStatus;
    private BigDecimal amount;
    private Boolean state = Boolean.TRUE;

}
