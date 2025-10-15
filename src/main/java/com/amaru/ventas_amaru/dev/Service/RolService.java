package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolResponseDTO;

import java.util.List;

public interface RolService {

    public List<RolRequestDTO> listarRoles();
    public RolRequestDTO guardarRol(RolRequestDTO rolRequestDTO);

    public RolRequestDTO eliminarRol(Integer id);

    public  RolRequestDTO actualizar (RolRequestDTO rolRequestDTO);
}
