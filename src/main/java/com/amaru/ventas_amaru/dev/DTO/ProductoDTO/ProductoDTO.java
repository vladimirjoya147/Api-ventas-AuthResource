package com.amaru.ventas_amaru.dev.DTO.ProductoDTO;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductoDTO {
    private Integer idProducto;
    private String codigoBarra;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String proveedorPreferido;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String path;
}

