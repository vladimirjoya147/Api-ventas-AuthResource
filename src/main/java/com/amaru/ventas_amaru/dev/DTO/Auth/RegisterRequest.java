package com.amaru.ventas_amaru.dev.DTO.Auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombreCompleto;
    private String username;
    private String password;
    private Integer idRol;
}
