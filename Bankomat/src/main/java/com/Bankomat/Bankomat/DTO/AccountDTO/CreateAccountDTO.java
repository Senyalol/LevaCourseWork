package com.Bankomat.Bankomat.DTO.AccountDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CreateAccountDTO {

    private Integer account_id;
    private Integer user_id;
    private Integer bank_id;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private Instant createdAt;

}
