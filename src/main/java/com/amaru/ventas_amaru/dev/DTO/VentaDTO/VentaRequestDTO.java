package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VentaRequestDTO {
    private Integer idCliente;
    private Long idUsuario;
    private BigDecimal totalVenta;
    private String metodoPago;
    private List<DetalleVentaRequestDTO> detalles;
}
