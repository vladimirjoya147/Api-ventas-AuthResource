package com.amaru.ventas_amaru.dev.DTO.ProductoDTO;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductoRequest {

    private String codigoBarra;
    private String nombre;
    private String descripcion;
    private Integer idCategoria;
    private Integer idProveedorPreferido;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private Boolean activo;
    private String path;
}
