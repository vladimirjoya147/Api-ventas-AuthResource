package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.ComprasRequest;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.ComprasResponse;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.DetalleComprasRequest;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.DetalleComprasResponse;
import com.amaru.ventas_amaru.dev.DTO.Usuario.UsuarioRequest;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Feign.ComprasFeignCLient;
import com.amaru.ventas_amaru.dev.Feign.UsuariosFeignClient;
import com.amaru.ventas_amaru.dev.Repository.ProductoRepository;
import com.amaru.ventas_amaru.dev.Repository.ProveedorRepository;
import com.amaru.ventas_amaru.dev.Repository.UsuarioRepository;
import com.amaru.ventas_amaru.dev.Service.ComprasService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComprasServiceImplement implements ComprasService {

    private final ProductoRepository  productoRepository;
    private final ComprasFeignCLient comprasFeignCLient;
    private final ProveedorRepository proveedorRepository;
    private final UsuariosFeignClient usuariosFeignClient;

    public ComprasServiceImplement(ProductoRepository productoRepository, ComprasFeignCLient comprasFeignCLient, ProveedorRepository proveedorRepository, UsuariosFeignClient usuariosFeignClient) {
        this.productoRepository = productoRepository;
        this.comprasFeignCLient = comprasFeignCLient;
        this.proveedorRepository = proveedorRepository;
        this.usuariosFeignClient = usuariosFeignClient;
    }


    @Override
    public List<ComprasResponse> listarCompras() {
        List<ComprasRequest> compras = comprasFeignCLient.listarCompras();

        Set<Integer> idProveedores = compras.stream()
                .map(ComprasRequest::getIdProveedor)
                .collect(Collectors.toSet());

        Set<Long> idUsuarios = compras.stream()
                .map(ComprasRequest::getIdUsuario)
                .collect(Collectors.toSet());

        Map<Integer, String> proveedoresMap = proveedorRepository.findAllById(idProveedores)
                .stream()
                .collect(Collectors.toMap(Proveedor::getIdProveedor, Proveedor::getNombreProveedor));

        List<UsuarioRequest> usuarios = obtenerUsuariosConCircuitBreaker(idUsuarios);

        Map<Long, String> usuariosMap = usuarios.stream()
                .collect(Collectors.toMap(UsuarioRequest::getId, UsuarioRequest::getNombreCompleto));

        return compras.stream()
                .map(c -> new ComprasResponse(
                        c.getIdCompra(),
                        proveedoresMap.get(c.getIdProveedor()),
                        usuariosMap.get(c.getIdUsuario()),
                        c.getFechaCompra(),
                        c.getTotalCompra(),
                        c.getFechaRecepcion(),
                        c.getEstado()
                ))
                .toList();
    }

    @CircuitBreaker(name = "authServiceCircuit", fallbackMethod = "usuariosFallback")
    public List<UsuarioRequest> obtenerUsuariosConCircuitBreaker(Set<Long> ids) {
        return usuariosFeignClient.obtenerUsuariosPorId(ids);
    }


    @Override
    public List<DetalleComprasResponse> listarDetalleCompras(Integer idCompra) {
        List<DetalleComprasRequest>  listarCompras = comprasFeignCLient.listarDetalleCompras(idCompra);

        Set<Integer> idProductos = listarCompras.stream().
                map(DetalleComprasRequest::getIdProducto).collect(Collectors.toSet());

        Map<Integer, String> mapProductos = productoRepository.findAllById(idProductos)
                .stream().collect(Collectors.toMap(Producto::getIdProducto,Producto::getNombre));

        return listarCompras.stream().map(r->new DetalleComprasResponse(
                r.getIdDetalleCompra(),
                mapProductos.get(r.getIdProducto()),
                r.getCantidad(),
                r.getCostoUnitario()
        )).toList();
    }


    public List<UsuarioRequest> usuariosFallback(Set<Long> ids, Throwable ex) {
        System.out.println("Auth-Service no disponible: " + ex.getMessage());
        return ids.stream()
                .map(id -> new UsuarioRequest(id, "Usuario no disponible"))
                .toList();
    }
}
