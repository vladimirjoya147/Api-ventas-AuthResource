package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaMapDTO;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetalleMapper {

    DetalleMapper mapper = Mappers.getMapper(DetalleMapper.class);

    VentaMapDTO toDTO (DetalleVenta detalleVenta);

}
