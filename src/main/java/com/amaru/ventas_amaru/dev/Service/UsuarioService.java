package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.Auth.RegisterRequest;

public interface UsuarioService {

    public String register(RegisterRequest request);

    public Integer buscarPorUsername(String nombre);
}
