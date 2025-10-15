package com.amaru.ventas_amaru.dev.Repository;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("""
            Select p from Producto  p where p.activo=true
            """)
    public Page<Producto> listarProductosActivos (Pageable pageable);

    @Query("SELECT p FROM Producto p " +
            "WHERE p.activo = true " +
            "  AND (LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "       OR p.codigoBarra LIKE CONCAT('%', :filtro, '%'))")
    Page<Producto> buscarPorNombreOcodigo(@Param("filtro") String filtro, Pageable pageable);

    public Producto findByCodigoBarra(String codigo);

}
