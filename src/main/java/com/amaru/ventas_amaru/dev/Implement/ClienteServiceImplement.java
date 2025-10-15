package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.ClienteDTO.ClienteDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.PageResponse;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import com.amaru.ventas_amaru.dev.Mapper.ClienteMapper;
import com.amaru.ventas_amaru.dev.Repository.ClienteRepository;
import com.amaru.ventas_amaru.dev.Service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.amaru.ventas_amaru.dev.Mapper.ClienteMapper.clienteMapper;
import static com.amaru.ventas_amaru.dev.Mapper.ProductoMapper.mapper;

@Service
@RequiredArgsConstructor
public class ClienteServiceImplement implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public PageResponse<ClienteDTO> listarPorNombreOdocumentoActivo(String filtro, Pageable pageable) {
       Page<ClienteDTO> listarClientes = clienteRepository.buscarPorNombreODocumento(filtro,pageable)
               .map(clienteMapper::toDTO);
        return new PageResponse<>(
                listarClientes.getContent(),
                listarClientes.getNumber(),
                listarClientes.getSize(),
                listarClientes.getTotalElements(),
                listarClientes.getTotalPages(),
                listarClientes.isFirst(),
                listarClientes.isLast()
        );
    }

    @Override
    public ClienteDTO actDesactCliente(Integer id, boolean activo) {
        if (!clienteRepository.existsById(id)){
            throw new EntityNotFoundException("Cliente no encontrado");
        }
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Cliente no encontrado con el id "+id));
        cliente.setActivo(activo);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(guardado);
    }

    @Override
    public PageResponse<ClienteDTO> listarClientesActivos(Pageable pageable) {
        Page<ClienteDTO> lista = clienteRepository.listarClienteActivos(pageable).map(clienteMapper::toDTO);
        return new PageResponse<>(
                lista.getContent(),
                lista.getNumber(),
                lista.getSize(),
                lista.getTotalElements(),
                lista.getTotalPages(),
                lista.isFirst(),
                lista.isLast()
        );
    }

    @Override
    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setActivo(true);
        Cliente guardar = clienteRepository.save(cliente);
        return clienteMapper.toDTO(guardar);
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteDTO.getIdCliente()).orElseThrow(
                ()->new EntityNotFoundException("Cliente no encontrado con el id "+ clienteDTO.getIdCliente()));
        clienteDTO.setActivo(true);
        Cliente guardar = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDTO(clienteRepository.save(guardar));
    }

    @Override
    public ClienteDTO buscarPorID(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Cliente no encontrado con el id "+id));
        return clienteMapper.toDTO(cliente);
    }

}
