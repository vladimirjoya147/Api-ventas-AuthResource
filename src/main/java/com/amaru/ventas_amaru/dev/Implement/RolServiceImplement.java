package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolResponseDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Rol;
import com.amaru.ventas_amaru.dev.Mapper.RolMapper;
import com.amaru.ventas_amaru.dev.Repository.RolRepository;
import com.amaru.ventas_amaru.dev.Service.RolService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.amaru.ventas_amaru.dev.Mapper.RolMapper.rolMap;

@Service
public class RolServiceImplement implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImplement(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<RolRequestDTO> listarRoles() {
        List<Rol> rol = rolRepository.findAll();
        return rol.stream().map(rolMap::toDTO).collect(Collectors.toList());
    }

    @Override
    public RolRequestDTO guardarRol(RolRequestDTO rolRequestDTO) {
        Rol rol = rolMap.toEntity(rolRequestDTO);
        return rolMap.toDTO(rolRepository.save(rol));
    }

    @Override
    public RolRequestDTO eliminarRol(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No se encontro rol con el id "+id));
        rolRepository.deleteById(id);
        return rolMap.toDTO(rol);
    }

    @Override
    public RolRequestDTO actualizar(RolRequestDTO rolRequestDTO) {

        Rol rol = rolRepository.findById(rolRequestDTO.getIdRol())
                .orElseThrow(()->new EntityNotFoundException("no se econtro el rol con id "+rolRequestDTO.getIdRol()));
        Rol guardar = rolMap.toEntity(rolRequestDTO);
        return rolMap.toDTO(rolRepository.save(guardar));
    }
}
