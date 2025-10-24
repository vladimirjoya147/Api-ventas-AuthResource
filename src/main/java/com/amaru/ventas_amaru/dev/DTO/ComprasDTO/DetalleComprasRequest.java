package com.amaru.ventas_amaru.dev.DTO.ComprasDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleComprasRequest {
    private Integer idDetalleCompra;
    private Integer idCompra;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal costoUnitario;
}
