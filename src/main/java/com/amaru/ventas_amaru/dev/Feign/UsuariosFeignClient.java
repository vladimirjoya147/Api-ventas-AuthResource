package com.amaru.ventas_amaru.dev.Feign;

import com.amaru.ventas_amaru.dev.DTO.Usuario.UsuarioRequest;
import com.amaru.ventas_amaru.dev.Security.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(name = "auth-server",
        fallback = UsuariosFeignClientFallback.class)
public interface UsuariosFeignClient {

    @GetMapping("/user/{id}")
    UsuarioRequest obtenerUsuarioPorId(@PathVariable Long id);

    @PostMapping("user/list")
    List<UsuarioRequest> obtenerUsuariosPorId(@RequestBody Set<Long> idUsuarios);

}
