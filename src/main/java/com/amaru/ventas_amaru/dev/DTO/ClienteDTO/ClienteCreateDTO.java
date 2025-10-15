package com.amaru.ventas_amaru.dev.DTO.ClienteDTO;

import lombok.Data;

@Data
public class ClienteCreateDTO {
    private Integer idCliente;
    private String nombreCliente;
    private String documentoIdentidad;
    private String telefono;
    private String email;
    private String direccion;
}
