package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.Security.CustomUserDetails;
import com.amaru.ventas_amaru.dev.Security.JwtUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final JwtUserService jwtUserService;

    public UsuarioController(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    /**
     * Endpoint que solo usa información del JWT
     */
    @GetMapping("/profile")
    public Object getProfile(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "username", jwt.getSubject(),
                "email", jwt.getClaim("email"),
                "roles", jwt.getClaim("roles")
        );
    }

    /**
     * Endpoint que carga información adicional de la BD
     */
    @GetMapping("/profile/full")
    public CustomUserDetails getFullProfile(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return jwtUserService.loadUserFromJwt(username);
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminData() {
        return "Datos solo para administradores";
    }

    @PostMapping("/ventas")
    @PreAuthorize("hasAuthority('CREAR_VENTA')")
    public String crearVenta() {
        return "Venta creada";
    }
}