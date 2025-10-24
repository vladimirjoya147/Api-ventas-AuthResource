package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VentaRequestDTO {
    private Integer idCliente;
<<<<<<< HEAD
    private Integer idUsuario;
=======
    private Long idUsuario;
>>>>>>> 48489ff (desacoplando usuarios)
    private BigDecimal totalVenta;
    private String metodoPago;
    private List<DetalleVentaRequestDTO> detalles;
}
