package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaMapDTO;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-12T22:32:09-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
public class DetalleMapperImpl implements DetalleMapper {

    @Override
    public VentaMapDTO toDTO(DetalleVenta detalleVenta) {
        if ( detalleVenta == null ) {
            return null;
        }

        Integer cantidad = null;
        BigDecimal precioUnitario = null;
        BigDecimal descuento = null;

        cantidad = detalleVenta.getCantidad();
        precioUnitario = detalleVenta.getPrecioUnitario();
        descuento = detalleVenta.getDescuento();

        String nombreProducto = null;

        VentaMapDTO ventaMapDTO = new VentaMapDTO( nombreProducto, cantidad, precioUnitario, descuento );

        return ventaMapDTO;
    }
}
