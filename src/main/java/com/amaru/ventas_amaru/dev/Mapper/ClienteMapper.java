package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ClienteDTO.ClienteDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper
public interface ClienteMapper {

    ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

   Cliente toEntity (ClienteDTO clienteDTO);

   ClienteDTO toDTO (Cliente cliente);

}
