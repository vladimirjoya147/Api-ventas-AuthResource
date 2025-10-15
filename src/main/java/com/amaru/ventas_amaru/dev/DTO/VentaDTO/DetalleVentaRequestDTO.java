package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleVentaRequestDTO {
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento ;
}
