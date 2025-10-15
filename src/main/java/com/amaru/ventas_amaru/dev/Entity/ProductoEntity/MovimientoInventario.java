package com.amaru.ventas_amaru.dev.Entity.ProductoEntity;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Enum.TipoMovimiento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "stock_anterior", nullable = false)
    private Integer stockAnterior;

    @Column(name = "stock_nuevo", nullable = false)
    private Integer stockNuevo;

    @CreationTimestamp
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "referencia_id")
    private Integer referenciaId;
}