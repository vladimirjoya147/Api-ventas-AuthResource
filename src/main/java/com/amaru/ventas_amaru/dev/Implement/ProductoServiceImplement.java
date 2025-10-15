package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.PageResponse;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoRequest;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import com.amaru.ventas_amaru.dev.Repository.CategoriaRepository;
import com.amaru.ventas_amaru.dev.Repository.ProductoRepository;
import com.amaru.ventas_amaru.dev.Repository.ProveedorRepository;
import com.amaru.ventas_amaru.dev.Service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.amaru.ventas_amaru.dev.Mapper.ProductoMapeado.getProducto;
import static com.amaru.ventas_amaru.dev.Mapper.ProductoMapper.mapper;

@Service
@RequiredArgsConstructor
public class ProductoServiceImplement implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;
    @Override
    public PageResponse<ProductoDTO> listarProducto(Pageable pageable) {
        Page<ProductoDTO> page = productoRepository.listarProductosActivos(pageable)
                .map(mapper::toDto);
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    @Override
    public ProductoDTO productoActivar(Integer id, Boolean activo) {
        if(!productoRepository.existsById(id)){
            throw new EntityNotFoundException("Producto no encontrado");
        }
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no se encontro id "+ id));
        producto.setActivo(activo);
        Producto guardar = productoRepository.save(producto);
        return mapper.toDto(guardar);
    }

    @Override
    public ProductoDTO productoActualizar(ProductoDTO dto) {
        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(()->new EntityNotFoundException("no se econtro producto con el id "+dto.getIdProducto()));
        producto.setIdProducto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setCodigoBarra(dto.getCodigoBarra());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setActivo(dto.getActivo());
        Producto guardar = productoRepository.save(producto);
        return mapper.toDto(guardar);
    }

    @Override
    public PageResponse<ProductoDTO> listarPorNombreCodigo(String filtro, Pageable pageable) {
        Page<ProductoDTO> lista = productoRepository.buscarPorNombreOcodigo(filtro, pageable).map(mapper::toDto);
        return new PageResponse<>(
                lista.getContent(),
                lista.getNumber(),
                lista.getSize(),
                lista.getTotalElements(),
                lista.getTotalPages(),
                lista.isFirst(),
                lista.isLast()
        );
    }

    @Override
    public ProductoDTO guardarProducto(ProductoRequest producto) {
        Categoria categoria = categoriaRepository.findById(producto.getIdCategoria())
                .orElseThrow(()->new EntityNotFoundException("No se encontro la categoria "+ producto.getIdCategoria()));
        Proveedor proveedor = proveedorRepository.findById(producto.getIdProveedorPreferido())
                .orElseThrow(()->new EntityNotFoundException("Proveedor no se ha encontrado con el id " + producto.getIdProveedorPreferido()));
        Producto pro = getProducto(producto, categoria, proveedor);
        Producto guardado = productoRepository.save(pro);
        return mapper.toDto(guardado);
    }

    @Override
    public ProductoDTO buscarPorCodigo(String codigo) {
        //String recorte = codigo.substring(2,12);
        Producto buscar = productoRepository.findByCodigoBarra(codigo);
        return mapper.toDto(buscar);
    }


}
