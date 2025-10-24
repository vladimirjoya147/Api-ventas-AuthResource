package com.amaru.ventas_amaru.dev.DTO.ComprasDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
public class ComprasResponse {
    private Integer idCompra;
    private String nombreProveedor;
    private String nombreCompleto;
    private LocalDateTime fechaCompra;
    private BigDecimal totalCompra;
    private LocalDateTime fechaRecepcion;
    private String estado;
}
