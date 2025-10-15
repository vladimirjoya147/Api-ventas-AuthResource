package com.amaru.ventas_amaru.dev.Repository;

import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
}
