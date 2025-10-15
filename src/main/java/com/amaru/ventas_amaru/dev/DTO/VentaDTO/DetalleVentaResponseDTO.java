package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleVentaResponseDTO {
    private Integer idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
}
