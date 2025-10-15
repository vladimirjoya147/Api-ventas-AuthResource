package com.amaru.ventas_amaru.dev.Repository;

import com.amaru.ventas_amaru.dev.Entity.VentaEntity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
