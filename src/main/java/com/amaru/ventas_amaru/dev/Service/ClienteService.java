package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.ClienteDTO.ClienteDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.PageResponse;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    public PageResponse<ClienteDTO> listarPorNombreOdocumentoActivo(String filtro, Pageable pageable);

    public ClienteDTO actDesactCliente(Integer id, boolean activo);

    public PageResponse<ClienteDTO> listarClientesActivos(Pageable pageable);

    public ClienteDTO guardarCliente (ClienteDTO clienteDTO);

    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO);

    public ClienteDTO buscarPorID (Integer id);
}
