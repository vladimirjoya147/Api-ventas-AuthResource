package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VentaResponseDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idUsuario;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private String metodoPago;
    private String estado;
    private List<DetalleVentaResponseDTO> detalles;
}
