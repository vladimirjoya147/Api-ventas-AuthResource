package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.PermisoDTO.PermisoRequestDTO;

import java.util.List;

public interface PermisoService {

    public List<PermisoRequestDTO> listarPermiso ();
    public PermisoRequestDTO guardarPermiso (PermisoRequestDTO permisoRequestDTO);

    public PermisoRequestDTO actualizarPermiso(PermisoRequestDTO permisoRequestDTO);

    public PermisoRequestDTO eliminarPermiso(Integer id);

}
