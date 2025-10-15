package com.amaru.ventas_amaru.dev.Repository;

import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaDetalleRepository extends JpaRepository<DetalleVenta, Integer> {


    List<DetalleVenta> findByVentaIdVenta(Integer id);
}
