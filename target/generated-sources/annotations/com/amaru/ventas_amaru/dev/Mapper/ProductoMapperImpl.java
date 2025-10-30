package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProveedorResponseDTO;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T01:56:24-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDTO toDto(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setCategoria( productoCategoriaNombre( producto ) );
        productoDTO.setProveedorPreferido( productoProveedorPreferidoNombreProveedor( producto ) );
        productoDTO.setActivo( producto.getActivo() );
        productoDTO.setCodigoBarra( producto.getCodigoBarra() );
        productoDTO.setDescripcion( producto.getDescripcion() );
        productoDTO.setFechaActualizacion( producto.getFechaActualizacion() );
        productoDTO.setFechaCreacion( producto.getFechaCreacion() );
        productoDTO.setIdProducto( producto.getIdProducto() );
        productoDTO.setNombre( producto.getNombre() );
        productoDTO.setPath( producto.getPath() );
        productoDTO.setPrecioCompra( producto.getPrecioCompra() );
        productoDTO.setPrecioVenta( producto.getPrecioVenta() );
        productoDTO.setStock( producto.getStock() );
        productoDTO.setStockMinimo( producto.getStockMinimo() );

        return productoDTO;
    }

    @Override
    public List<ProductoDTO> toDtoList(List<Producto> productos) {
        if ( productos == null ) {
            return null;
        }

        List<ProductoDTO> list = new ArrayList<ProductoDTO>( productos.size() );
        for ( Producto producto : productos ) {
            list.add( toDto( producto ) );
        }

        return list;
    }

    @Override
    public ProveedorResponseDTO productoToDTO(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProveedorResponseDTO proveedorResponseDTO = new ProveedorResponseDTO();

        proveedorResponseDTO.setActivo( producto.getActivo() );

        return proveedorResponseDTO;
    }

    @Override
    public Producto productoToEntity(ProveedorResponseDTO proveedorResponseDTO) {
        if ( proveedorResponseDTO == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setActivo( proveedorResponseDTO.getActivo() );

        return producto;
    }

    private String productoCategoriaNombre(Producto producto) {
        Categoria categoria = producto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        return categoria.getNombre();
    }

    private String productoProveedorPreferidoNombreProveedor(Producto producto) {
        Proveedor proveedorPreferido = producto.getProveedorPreferido();
        if ( proveedorPreferido == null ) {
            return null;
        }
        return proveedorPreferido.getNombreProveedor();
    }
}
