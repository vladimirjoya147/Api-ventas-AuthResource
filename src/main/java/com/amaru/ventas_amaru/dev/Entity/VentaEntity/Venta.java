package com.amaru.ventas_amaru.dev.Entity.VentaEntity;

import com.amaru.ventas_amaru.dev.Entity.Cliente;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_venta", nullable = false, updatable = false)
    private LocalDateTime fechaVenta;

    @Column(name = "total_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 20)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoVenta estado = EstadoVenta.COMPLETADA;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    public enum MetodoPago {
        EFECTIVO,
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        TRANSFERENCIA
    }

    public enum EstadoVenta {
        COMPLETADA,
        ANULADA
    }
}