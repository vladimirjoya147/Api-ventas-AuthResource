package com.amaru.ventas_amaru.dev.DTO.ComprasDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
public class DetalleComprasResponse {

    private Integer idDetalleCompra;
    private String nombre;
    private Integer cantidad;
    private BigDecimal costoUnitario;
}
