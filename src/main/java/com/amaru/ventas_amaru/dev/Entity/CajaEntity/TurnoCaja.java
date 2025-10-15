package com.amaru.ventas_amaru.dev.Entity.CajaEntity;

import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Enum.EstadoTurno;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos_caja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TurnoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    @EqualsAndHashCode.Include
    private Integer idTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "fecha_apertura", nullable = false, updatable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "monto_inicial", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoInicial;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "monto_final_sistema", precision = 10, scale = 2)
    private BigDecimal montoFinalSistema;

    @Column(name = "monto_final_real", precision = 10, scale = 2)
    private BigDecimal montoFinalReal;

    @Column(name = "diferencia", precision = 10, scale = 2)
    private BigDecimal diferencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoTurno estado = EstadoTurno.ABIERTO;
}