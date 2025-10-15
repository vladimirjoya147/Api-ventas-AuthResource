package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.PermisoDTO.PermisoRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Permiso;
import com.amaru.ventas_amaru.dev.Mapper.PermiseMapper;
import com.amaru.ventas_amaru.dev.Repository.PermisoRepository;
import com.amaru.ventas_amaru.dev.Service.PermisoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.amaru.ventas_amaru.dev.Mapper.PermiseMapper.permiseMapper;

@Service
public class PermisoServiceImplement implements PermisoService {

    private final PermisoRepository permisoRepository;

    public PermisoServiceImplement(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    @Override
    public List<PermisoRequestDTO> listarPermiso() {
        List<PermisoRequestDTO> listarPermisos = permisoRepository.findAll()
                .stream().map(permiseMapper::toDTO)
                .toList();
        return listarPermisos;
    }

    @Override
    public PermisoRequestDTO guardarPermiso(PermisoRequestDTO permisoRequestDTO) {
        Permiso permiso = permiseMapper.toEntity(permisoRequestDTO);
        return permiseMapper.toDTO(permisoRepository.save(permiso));
    }

    @Override
    public PermisoRequestDTO actualizarPermiso(PermisoRequestDTO permisoRequestDTO) {
        if(!permisoRepository.existsById(permisoRequestDTO.getIdPermiso())){
            throw new EntityNotFoundException("Pemiso no econtrado con id"+permisoRequestDTO.getIdPermiso());
        }
        Permiso permiso = permiseMapper.toEntity(permisoRequestDTO);
        return permiseMapper.toDTO(permisoRepository.save(permiso)) ;
    }

    @Override
    public PermisoRequestDTO eliminarPermiso(Integer id) {
        Permiso  permiso = permisoRepository.findById(id)
                        .orElseThrow(()->new EntityNotFoundException("Permiso no encontraod id"+id));
        permisoRepository.deleteById(id);
        return permiseMapper.toDTO(permiso);
    }
}
