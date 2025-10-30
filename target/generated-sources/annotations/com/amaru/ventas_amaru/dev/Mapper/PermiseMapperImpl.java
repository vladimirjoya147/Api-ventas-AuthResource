package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.PermisoDTO.PermisoRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Permiso;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T01:56:24-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class PermiseMapperImpl implements PermiseMapper {

    @Override
    public PermisoRequestDTO toDTO(Permiso permiso) {
        if ( permiso == null ) {
            return null;
        }

        PermisoRequestDTO permisoRequestDTO = new PermisoRequestDTO();

        permisoRequestDTO.setDescripcion( permiso.getDescripcion() );
        permisoRequestDTO.setIdPermiso( permiso.getIdPermiso() );
        permisoRequestDTO.setNombrePermiso( permiso.getNombrePermiso() );

        return permisoRequestDTO;
    }

    @Override
    public Permiso toEntity(PermisoRequestDTO permisoRequestDTO) {
        if ( permisoRequestDTO == null ) {
            return null;
        }

        Permiso permiso = new Permiso();

        permiso.setDescripcion( permisoRequestDTO.getDescripcion() );
        permiso.setIdPermiso( permisoRequestDTO.getIdPermiso() );
        permiso.setNombrePermiso( permisoRequestDTO.getNombrePermiso() );

        return permiso;
    }
}
