package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.Service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolRequestDTO>> listarRoles(){
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @PostMapping("/guardar")
    public ResponseEntity<RolRequestDTO> guardarRoles(@RequestBody RolRequestDTO rolRequestDTO) {
        return ResponseEntity.ok(rolService.guardarRol(rolRequestDTO));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<RolRequestDTO> actualizarRoles (@RequestBody  RolRequestDTO rolRequestDTO){
        return ResponseEntity.ok(rolService.actualizar(rolRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RolRequestDTO> eliminarRol (@PathVariable Integer id){
        return ResponseEntity.ok(rolService.eliminarRol(id));
    }

}
