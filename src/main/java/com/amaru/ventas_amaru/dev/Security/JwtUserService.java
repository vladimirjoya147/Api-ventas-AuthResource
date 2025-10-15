package com.amaru.ventas_amaru.dev.Security;

import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class JwtUserService {

    private final UsuarioRepository usuarioRepository;

    public JwtUserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public CustomUserDetails loadUserFromJwt(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username
                ));
        return new CustomUserDetails(usuario);
    }

    public List<GrantedAuthority> getAuthoritiesFromDatabase(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username
                ));

        List<GrantedAuthority> authorities = usuario.getRol().getPermisos().stream()
                .map(permiso -> new SimpleGrantedAuthority(permiso.getNombrePermiso()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + usuario.getRol().getNombreRol()
        ));

        return authorities;
    }
}