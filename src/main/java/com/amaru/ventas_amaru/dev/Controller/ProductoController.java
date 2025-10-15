package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.PageResponse;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoRequest;
import com.amaru.ventas_amaru.dev.Service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductoDTO>> listarTodosLosProductos (
             @RequestParam(defaultValue = "0") int pagina,
             @RequestParam(defaultValue = "10") int tamano,
             @RequestParam(defaultValue = "nombre" ) String ordenar
    ){
        Pageable pageable = PageRequest.of(pagina,tamano, Sort.by(ordenar));
        return ResponseEntity.ok(productoService.listarProducto(pageable));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ProductoDTO> activarDesactivarProducto(@PathVariable Integer id,@RequestParam boolean activo){
        return ResponseEntity.ok(productoService.productoActivar(id,activo));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(productoService.productoActualizar(productoDTO));
    }

    @GetMapping("/lista")
    public ResponseEntity<PageResponse<ProductoDTO>> listarPorCod (
            @RequestParam String busqueda,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamano
    ){
        Pageable pageable = PageRequest.of(pagina, tamano);
        return ResponseEntity.ok(productoService.listarPorNombreCodigo(busqueda, pageable));
    }

    @PostMapping("/guardar")
    public ResponseEntity<ProductoDTO> guardarProductos (@RequestBody ProductoRequest productoRequest){
        return ResponseEntity.ok(productoService.guardarProducto(productoRequest));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ProductoDTO> buscarPorCodigo(@RequestParam String codigo){
        return ResponseEntity.ok(productoService.buscarPorCodigo(codigo));
    }
}
