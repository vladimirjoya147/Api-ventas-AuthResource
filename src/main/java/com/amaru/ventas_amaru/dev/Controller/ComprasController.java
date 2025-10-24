package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.ComprasResponse;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.DetalleComprasResponse;
import com.amaru.ventas_amaru.dev.Service.ComprasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class ComprasController {

    private final ComprasService comprasService;

    public ComprasController(ComprasService comprasService) {
        this.comprasService = comprasService;
    }

    @GetMapping
    public ResponseEntity<List<ComprasResponse>> getCompras() {
        return ResponseEntity.ok().body(comprasService.listarCompras());
    }

    @GetMapping("/detalle/{id}")
    public  ResponseEntity<List<DetalleComprasResponse>> getComprasById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(comprasService.listarDetalleCompras(id));
    }
}
