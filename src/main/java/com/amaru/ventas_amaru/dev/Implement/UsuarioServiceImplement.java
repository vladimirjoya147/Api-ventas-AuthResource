package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.Auth.RegisterRequest;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Rol;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Repository.RolRepository;
import com.amaru.ventas_amaru.dev.Repository.UsuarioRepository;
import com.amaru.ventas_amaru.dev.Service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImplement implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {

        String mensaje="";
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()){
            return mensaje="El username ya existe";
        }
        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(()->new EntityNotFoundException("Rol no encontrado "));
        Usuario nuevoUsuario = Usuario.builder()
                .nombreCompleto(request.getNombreCompleto())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(rol)
                .activo(true)
                .moneda("S/")
                .build();
        usuarioRepository.save(nuevoUsuario);
        return mensaje ="Usuario registrado con exito";
    }

    @Override
    public Integer buscarPorUsername(String nombre) {
        Optional<Usuario> user = usuarioRepository.findByUsername(nombre);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el usuario con el nombre" + nombre);
        }

        return user.get().getIdUsuario();
    }
}
