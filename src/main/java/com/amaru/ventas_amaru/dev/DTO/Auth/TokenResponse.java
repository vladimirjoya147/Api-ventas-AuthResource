package com.amaru.ventas_amaru.dev.DTO.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
public class TokenResponse {
    @JsonProperty("access_token")
    String accesToken;
    @JsonProperty("refresh_token")
    String refreshToken;
}
