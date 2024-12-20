package com.Bankomat.Bankomat.DTO.TransactionDTO;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

@Data
public class ShortTransactionDTO {
    private Integer transaction_id;
    private Integer account_id;
    private Integer atm_id;
    private String transactionType;
    private BigDecimal amount;
    private Instant transactionDate;
}
