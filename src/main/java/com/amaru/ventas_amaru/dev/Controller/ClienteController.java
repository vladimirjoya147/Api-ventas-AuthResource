package com.amaru.ventas_amaru.dev.Controller;

import com.amaru.ventas_amaru.dev.DTO.ClienteDTO.ClienteDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.PageResponse;
import com.amaru.ventas_amaru.dev.Service.ClienteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ClienteDTO>> listarClientesActivos (
            @RequestParam(defaultValue ="0" ) int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "nombreCliente")String nombre){
        Pageable pageable = PageRequest.of(pagina,tamanio, Sort.by(nombre));
        return ResponseEntity.ok(clienteService.listarClientesActivos(pageable));
    }

    @GetMapping("/listar")
    public ResponseEntity<PageResponse<ClienteDTO>> listarClientesPorNombreDocumento (
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "nombreCliente") String nombre,
            @RequestParam(defaultValue = "cliente")String busqueda)
    {
        Pageable page = PageRequest.of(pagina,tamanio, Sort.by(nombre));
        return ResponseEntity.ok(clienteService.listarPorNombreOdocumentoActivo(busqueda, page));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDTO> activarDesactivarCliente (@PathVariable Integer id,@RequestParam boolean activo){
        return ResponseEntity.ok(clienteService.actDesactCliente(id,activo));
    }

    @PostMapping("/guardar")
    public ResponseEntity<ClienteDTO> guardarCliente (@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.guardarCliente(clienteDTO));
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<ClienteDTO> actualizarCliente (@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.actualizarCliente(clienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> bucarClienteId (@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.buscarPorID(id));
    }
}
