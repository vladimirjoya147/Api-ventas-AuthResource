package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.PermisoDTO.PermisoRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermiseMapper {

    PermiseMapper permiseMapper = Mappers.getMapper(PermiseMapper.class);

    PermisoRequestDTO toDTO(Permiso permiso);

    Permiso toEntity(PermisoRequestDTO permisoRequestDTO);
}
