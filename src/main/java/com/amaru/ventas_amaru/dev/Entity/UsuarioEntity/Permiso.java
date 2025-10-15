package com.amaru.ventas_amaru.dev.Entity.UsuarioEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permisos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    @EqualsAndHashCode.Include
    private Integer idPermiso;

    @Column(name = "nombre_permiso", nullable = false, unique = true, length = 100)
    private String nombrePermiso;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ManyToMany(mappedBy = "permisos")
    private Set<Rol> roles;
}
