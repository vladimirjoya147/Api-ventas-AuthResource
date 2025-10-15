package com.amaru.ventas_amaru.dev.DTO.CategoriaDTO;

import lombok.Data;

@Data
public class CategoriaRequestDTO {
    private Integer idCategoria;
    private String nombre;
    private Boolean activo;
}
