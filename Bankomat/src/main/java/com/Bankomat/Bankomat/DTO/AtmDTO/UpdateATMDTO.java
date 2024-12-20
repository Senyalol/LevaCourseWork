package com.Bankomat.Bankomat.DTO.AtmDTO;

import java.time.Instant;
import lombok.Data;

@Data
public class UpdateATMDTO {
    private Integer atm_id;
    private String location;
    private Integer bank_id;
    private String status;
    private Instant lastMaintenance;
}
