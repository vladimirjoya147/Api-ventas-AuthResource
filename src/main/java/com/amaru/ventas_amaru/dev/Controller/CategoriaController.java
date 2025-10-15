package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaDTO;
import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.amaru.ventas_amaru.dev.Service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodasCat (){
        return ResponseEntity.ok(categoriaService.listarCat());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> catPorId (@PathVariable Integer id){
        return ResponseEntity.ok(categoriaService.buscarCatPorId(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<CategoriaDTO> guardarCategoria (@RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.ok(categoriaService.guardarCat(categoriaRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaDTO> desactivarCat (@PathVariable Integer id){
        return ResponseEntity.ok(categoriaService.desctivar(id));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<CategoriaDTO> actualizarCategoria (@RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.ok(categoriaService.actualizar(categoriaRequestDTO));
    }

}
