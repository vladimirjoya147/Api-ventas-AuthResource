package com.amaru.ventas_amaru.dev.Entity.ProductoEntity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    @EqualsAndHashCode.Include
    private Integer idProveedor;

    @Column(name = "nombre_proveedor", nullable = false, length = 150)
    private String nombreProveedor;

    @Column(name = "ruc", length = 20, unique = true)
    private String ruc;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "proveedorPreferido", fetch = FetchType.LAZY)
    private Set<Producto> productos = new HashSet<>();
}