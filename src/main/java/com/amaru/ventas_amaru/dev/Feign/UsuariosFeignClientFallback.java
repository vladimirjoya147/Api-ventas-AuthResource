package com.amaru.ventas_amaru.dev.Feign;

import com.amaru.ventas_amaru.dev.DTO.Usuario.UsuarioRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuariosFeignClientFallback implements UsuariosFeignClient {

    @Override
    public UsuarioRequest obtenerUsuarioPorId(Long id) {
        UsuarioRequest fallback = new UsuarioRequest();
        fallback.setId(id);
        fallback.setNombreCompleto("Usuario no disponible");
        return fallback;
    }

    @Override
    public List<UsuarioRequest> obtenerUsuariosPorId(Set<Long> idUsuarios) {
        if (idUsuarios == null || idUsuarios.isEmpty()) {
            return Collections.emptyList();
        }
        return idUsuarios.stream()
                .map(id -> {
                    UsuarioRequest usuario = new UsuarioRequest();
                    usuario.setId(id);
                    usuario.setNombreCompleto("Usuario desconocido");
                    return usuario;
                })
                .collect(Collectors.toList());
    }
}