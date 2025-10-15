package com.amaru.ventas_amaru.dev.Entity.CompraEntity;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Enum.EstadoCompra;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    @EqualsAndHashCode.Include
    private Integer idCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "fecha_compra", nullable = false, updatable = false)
    private LocalDateTime fechaCompra;

    @Column(name = "total_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCompra;

    @Column(name = "fecha_recepcion")
    private LocalDateTime fechaRecepcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCompra estado = EstadoCompra.SOLICITADA;

    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> detalles = new ArrayList<>();

}