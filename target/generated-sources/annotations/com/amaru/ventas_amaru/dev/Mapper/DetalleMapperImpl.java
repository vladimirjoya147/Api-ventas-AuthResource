package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaMapDTO;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T01:56:24-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DetalleMapperImpl implements DetalleMapper {

    @Override
    public VentaMapDTO toDTO(DetalleVenta detalleVenta) {
        if ( detalleVenta == null ) {
            return null;
        }

        Integer cantidad = null;
        BigDecimal descuento = null;
        BigDecimal precioUnitario = null;

        cantidad = detalleVenta.getCantidad();
        descuento = detalleVenta.getDescuento();
        precioUnitario = detalleVenta.getPrecioUnitario();

        String nombreProducto = null;

        VentaMapDTO ventaMapDTO = new VentaMapDTO( nombreProducto, cantidad, precioUnitario, descuento );

        return ventaMapDTO;
    }
}
