package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProvedorRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProveedorResponseDTO;
import com.amaru.ventas_amaru.dev.Repository.ProveedorRepository;
import com.amaru.ventas_amaru.dev.Service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>>  listarProveedores (){
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }
    @PostMapping("/guardar")
    public ResponseEntity<ProveedorResponseDTO> guardar (@RequestBody ProvedorRequestDTO requestDTO){
        return ResponseEntity.ok(proveedorService.guardarProveedor(requestDTO));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestParam boolean activo){
        ProveedorResponseDTO proveedorResponseDTO = proveedorService.desactivar(id,activo);
        return ResponseEntity.ok(proveedorResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> buscarProveedoId(@PathVariable Integer id){
        return ResponseEntity.ok(proveedorService.buscaPorID(id));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<ProveedorResponseDTO> actualizar(@RequestBody ProvedorRequestDTO requestDTO){
        return ResponseEntity.ok(proveedorService.actualizarProveedor(requestDTO));
    }


}
