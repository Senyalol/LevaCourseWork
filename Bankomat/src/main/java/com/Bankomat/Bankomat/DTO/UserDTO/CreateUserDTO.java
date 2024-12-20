package com.Bankomat.Bankomat.DTO.UserDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateUserDTO {

    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private Instant createdAt;
}
