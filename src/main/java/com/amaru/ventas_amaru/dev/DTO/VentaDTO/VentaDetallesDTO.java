package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class VentaDetallesDTO {

    private Integer idVenta;
    private String nombreCliente;
    private String nombreUsuario;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private String metodoPago;
    private String estado;
    private List<VentaMapDTO> detalles;
}
