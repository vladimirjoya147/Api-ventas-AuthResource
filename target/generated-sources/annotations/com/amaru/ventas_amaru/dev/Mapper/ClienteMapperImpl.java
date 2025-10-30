package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ClienteDTO.ClienteDTO;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T01:56:24-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setActivo( clienteDTO.getActivo() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setDocumentoIdentidad( clienteDTO.getDocumentoIdentidad() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setIdCliente( clienteDTO.getIdCliente() );
        cliente.setNombreCliente( clienteDTO.getNombreCliente() );
        cliente.setTelefono( clienteDTO.getTelefono() );

        return cliente;
    }

    @Override
    public ClienteDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setActivo( cliente.getActivo() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setDocumentoIdentidad( cliente.getDocumentoIdentidad() );
        clienteDTO.setEmail( cliente.getEmail() );
        clienteDTO.setIdCliente( cliente.getIdCliente() );
        clienteDTO.setNombreCliente( cliente.getNombreCliente() );
        clienteDTO.setTelefono( cliente.getTelefono() );

        return clienteDTO;
    }
}
