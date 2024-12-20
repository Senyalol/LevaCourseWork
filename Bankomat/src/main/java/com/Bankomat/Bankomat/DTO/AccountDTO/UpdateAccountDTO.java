package com.Bankomat.Bankomat.DTO.AccountDTO;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

@Data
public class UpdateAccountDTO {
    private Integer account_id;
    private Integer user_id;
    private Integer bank_id;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private Instant createdAt;
}
