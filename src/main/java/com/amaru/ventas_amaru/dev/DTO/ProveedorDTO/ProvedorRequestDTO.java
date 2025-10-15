package com.amaru.ventas_amaru.dev.DTO.ProveedorDTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProvedorRequestDTO {
    private Integer idProveedor;
    private String nombreProveedor;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
}
