package com.amaru.ventas_amaru.dev.Repository;

import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoInventario, Integer> {
}
