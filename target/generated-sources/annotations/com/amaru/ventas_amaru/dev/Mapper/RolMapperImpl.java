package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Rol;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T01:56:24-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class RolMapperImpl implements RolMapper {

    @Override
    public Rol toEntity(RolRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setIdRol( requestDTO.getIdRol() );
        rol.setNombreRol( requestDTO.getNombreRol() );

        return rol;
    }

    @Override
    public RolRequestDTO toDTO(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolRequestDTO rolRequestDTO = new RolRequestDTO();

        rolRequestDTO.setIdRol( rol.getIdRol() );
        rolRequestDTO.setNombreRol( rol.getNombreRol() );

        return rolRequestDTO;
    }
}
