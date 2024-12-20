package com.Bankomat.Bankomat.DTO.BankDTO;
import lombok.Data;
@Data
public class UpdateBankDTO {
    private Integer bank_id;
    private String name;
    private String address;
    private String contactNumber;
}
