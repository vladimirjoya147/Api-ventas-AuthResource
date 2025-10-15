package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.PermisoDTO.PermisoRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Permiso;
import com.amaru.ventas_amaru.dev.Service.PermisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permiso")
public class PermisoController {

    private final PermisoService permisoService;


    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    @GetMapping
    public ResponseEntity<List<PermisoRequestDTO>> listarTodosPermiso (){
        return ResponseEntity.ok(permisoService.listarPermiso());
    }

    @PostMapping("/guardar")
    public ResponseEntity<PermisoRequestDTO> guardarPermiso (@RequestBody PermisoRequestDTO permisoRequestDTO){
        return ResponseEntity.ok(permisoService.guardarPermiso(permisoRequestDTO));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<PermisoRequestDTO> actualizarPermiso(@RequestBody PermisoRequestDTO permisoRequestDTO){
        return ResponseEntity.ok(permisoService.actualizarPermiso(permisoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PermisoRequestDTO> eliminarPermiso(@PathVariable Integer id){
        return ResponseEntity.ok(permisoService.eliminarPermiso(id));
    }
}
