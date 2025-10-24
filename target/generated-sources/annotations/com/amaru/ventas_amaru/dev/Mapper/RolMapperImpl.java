package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Rol;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2025-10-12T22:32:09-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
=======
    date = "2025-10-23T16:26:20-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
>>>>>>> 48489ff (desacoplando usuarios)
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
