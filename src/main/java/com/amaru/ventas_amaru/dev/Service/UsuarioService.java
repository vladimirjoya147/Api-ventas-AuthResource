package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.Auth.RegisterRequest;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioService {

    public String register(RegisterRequest request);

    public Integer buscarPorUsername(String nombre);
}
