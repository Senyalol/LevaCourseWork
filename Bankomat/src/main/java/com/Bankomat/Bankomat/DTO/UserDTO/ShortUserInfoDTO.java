package com.Bankomat.Bankomat.DTO.UserDTO;

import java.time.Instant;
import lombok.Data;
@Data
public class ShortUserInfoDTO {
    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private Instant createdAt;
}
