package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper
public interface RolMapper {
    RolMapper rolMap = Mappers.getMapper(RolMapper.class);

    public Rol toEntity (RolRequestDTO requestDTO);

    public RolRequestDTO toDTO (Rol rol);

}
