package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class VentaListaDTO {
    private Integer idVenta;
    private String mombreCliente;
    private String nombreUsuario;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private String metodoPago;
    private String estadoVenta;
    private String imgUrl;
}
