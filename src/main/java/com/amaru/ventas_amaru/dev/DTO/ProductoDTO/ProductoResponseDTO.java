package com.amaru.ventas_amaru.dev.DTO.ProductoDTO;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductoResponseDTO {
    private Integer idProducto;
    private String codigoBarra;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private Proveedor proveedorPreferido;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
