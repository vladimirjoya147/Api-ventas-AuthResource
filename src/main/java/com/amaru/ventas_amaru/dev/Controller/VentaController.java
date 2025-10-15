package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaDetallesDTO;
import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaListaDTO;
import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaResponse;
import com.amaru.ventas_amaru.dev.Repository.VentaRepository;
import com.amaru.ventas_amaru.dev.Service.VentaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }


    @PostMapping
    ResponseEntity<VentaResponse> guardarVenta(@RequestBody VentaRequestDTO ventaRequestDTO){
        VentaResponse ventaResponse = ventaService.guardarVenta(ventaRequestDTO);
        return ResponseEntity.ok(ventaResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<VentaDetallesDTO>  buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(ventaService.obtenerVentaPorId(id));
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarComprobante(@PathVariable Integer id) throws Exception {
        byte[] pdf = ventaService.generarComprobantePDF(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comprobante_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping
    public ResponseEntity<List<VentaListaDTO>> listarVentas (){
        return ResponseEntity.ok(ventaService.listarVentas());
    }
}
