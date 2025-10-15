package com.amaru.ventas_amaru.dev.DTO.ProveedorDTO;

import lombok.Data;

@Data
public class ProveedorResponseDTO {

    private Integer idProveedor;
    private String nombreProveedor;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
    private Boolean activo;
}
