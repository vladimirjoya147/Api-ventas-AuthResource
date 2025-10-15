package com.amaru.ventas_amaru.dev.DTO.PermisoDTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PermisoRequestDTO {
    private Integer idPermiso;
    private String nombrePermiso;
    private String descripcion;
}
