package com.amaru.ventas_amaru.dev.DTO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private  Long id;
    private  String nombreCompleto;
}
