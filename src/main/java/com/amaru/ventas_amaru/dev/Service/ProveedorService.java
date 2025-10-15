package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProvedorRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProveedorResponseDTO;

import java.util.List;

public interface ProveedorService {

    public List<ProveedorResponseDTO> listarProveedores ();

    public ProveedorResponseDTO guardarProveedor(ProvedorRequestDTO provedorRequestDTO);

    public ProveedorResponseDTO desactivar(Integer id, boolean estado);

    public ProveedorResponseDTO buscaPorID(Integer id);

    public  ProveedorResponseDTO actualizarProveedor(ProvedorRequestDTO provedorRequestDTO);
}
