package com.amaru.ventas_amaru.dev.Security;

import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;


        this.authorities = usuario.getRol().getPermisos().stream()
                .map(permiso -> new SimpleGrantedAuthority(permiso.getNombrePermiso()))
                .collect(Collectors.toList());


        this.authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + usuario.getRol().getNombreRol()
        ));
    }

    public CustomUserDetails(Usuario usuario, List<GrantedAuthority> authorities) {
        this.usuario = usuario;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario != null ? usuario.getPassword() : "";
    }

    @Override
    public String getUsername() {
        return usuario != null ? usuario.getUsername() : "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario != null && usuario.getActivo();
    }

    public Usuario getUsuario() {
        return usuario;
    }
}