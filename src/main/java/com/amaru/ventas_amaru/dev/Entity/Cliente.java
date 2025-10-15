package com.amaru.ventas_amaru.dev.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    @EqualsAndHashCode.Include
    private Integer idCliente;

    @Column(name = "nombre_cliente", nullable = false, length = 150)
    private String nombreCliente;

    @Column(name = "documento_identidad", length = 20, unique = true)
    private String documentoIdentidad;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
}
