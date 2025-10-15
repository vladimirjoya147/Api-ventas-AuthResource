package com.amaru.ventas_amaru.dev.DTO.ClienteDTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteDTO {
    private Integer idCliente;
    private String nombreCliente;
    private String documentoIdentidad;
    private String telefono;
    private String email;
    private String direccion;
    private Boolean activo;
}
