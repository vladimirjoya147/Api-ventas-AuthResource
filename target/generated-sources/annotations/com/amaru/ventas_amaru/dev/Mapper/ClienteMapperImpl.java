package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ClienteDTO.ClienteDTO;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
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
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setIdCliente( clienteDTO.getIdCliente() );
        cliente.setNombreCliente( clienteDTO.getNombreCliente() );
        cliente.setDocumentoIdentidad( clienteDTO.getDocumentoIdentidad() );
        cliente.setTelefono( clienteDTO.getTelefono() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setActivo( clienteDTO.getActivo() );

        return cliente;
    }

    @Override
    public ClienteDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente( cliente.getIdCliente() );
        clienteDTO.setNombreCliente( cliente.getNombreCliente() );
        clienteDTO.setDocumentoIdentidad( cliente.getDocumentoIdentidad() );
        clienteDTO.setTelefono( cliente.getTelefono() );
        clienteDTO.setEmail( cliente.getEmail() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setActivo( cliente.getActivo() );

        return clienteDTO;
    }
}
