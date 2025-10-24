package com.amaru.ventas_amaru.dev.DTO.ComprasDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprasRequest {
    private Integer idCompra;
    private Integer idProveedor;
    private Long idUsuario;
    private LocalDateTime fechaCompra;
    private BigDecimal totalCompra;
    private LocalDateTime fechaRecepcion;
    private String estado;
}
