package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VentaResponse {
    private Integer ventaId;
    private LocalDateTime fecha;
    private BigDecimal totalVenta;
    private String message;
}
