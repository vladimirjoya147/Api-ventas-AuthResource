package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.Venta;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VentaMapDTO {

    private String  nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
}
