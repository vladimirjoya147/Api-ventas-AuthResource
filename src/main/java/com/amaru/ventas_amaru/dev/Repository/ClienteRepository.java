package com.amaru.ventas_amaru.dev.Repository;

import com.amaru.ventas_amaru.dev.Entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c " +
            "WHERE c.activo = true " +
            "  AND (LOWER(c.nombreCliente) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "       OR c.documentoIdentidad LIKE CONCAT('%', :filtro, '%'))")
    Page<Cliente> buscarPorNombreODocumento(@Param("filtro") String filtro, Pageable pageable);


    @Query("SELECT c FROM Cliente c WHERE c.activo=true")
    Page<Cliente> listarClienteActivos(Pageable pageable);
}
