package com.amaru.ventas_amaru.dev.Entity.CajaEntity;

import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_caja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovimientoCaja {

    public enum TipoMovimientoCaja {
        INGRESO, EGRESO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_caja")
    @EqualsAndHashCode.Include
    private Integer idMovimientoCaja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turno", nullable = false)
    private TurnoCaja turno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimientoCaja tipoMovimiento;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;
}