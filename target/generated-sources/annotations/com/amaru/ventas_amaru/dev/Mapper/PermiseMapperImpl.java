package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.PermisoDTO.PermisoRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Permiso;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-12T22:32:08-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
public class PermiseMapperImpl implements PermiseMapper {

    @Override
    public PermisoRequestDTO toDTO(Permiso permiso) {
        if ( permiso == null ) {
            return null;
        }

        PermisoRequestDTO permisoRequestDTO = new PermisoRequestDTO();

        permisoRequestDTO.setIdPermiso( permiso.getIdPermiso() );
        permisoRequestDTO.setNombrePermiso( permiso.getNombrePermiso() );
        permisoRequestDTO.setDescripcion( permiso.getDescripcion() );

        return permisoRequestDTO;
    }

    @Override
    public Permiso toEntity(PermisoRequestDTO permisoRequestDTO) {
        if ( permisoRequestDTO == null ) {
            return null;
        }

        Permiso permiso = new Permiso();

        permiso.setIdPermiso( permisoRequestDTO.getIdPermiso() );
        permiso.setNombrePermiso( permisoRequestDTO.getNombrePermiso() );
        permiso.setDescripcion( permisoRequestDTO.getDescripcion() );

        return permiso;
    }
}
