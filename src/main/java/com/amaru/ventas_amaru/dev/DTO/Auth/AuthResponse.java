package com.amaru.ventas_amaru.dev.DTO.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;
    private String refreshToken;

}
