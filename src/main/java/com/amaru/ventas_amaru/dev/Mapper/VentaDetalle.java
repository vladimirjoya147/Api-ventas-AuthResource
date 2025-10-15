package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaMapDTO;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;

import java.util.ArrayList;
import java.util.List;

public class VentaDetalle {

    public static List<VentaMapDTO> listarVentasMap(List<DetalleVenta>  detalleVentas){
        return detalleVentas
                .stream().map(d->new VentaMapDTO(
                        d.getProducto().getNombre(),
                        d.getCantidad(),
                        d.getPrecioUnitario(),
                        d.getDescuento()
                )).toList();
    }
}
