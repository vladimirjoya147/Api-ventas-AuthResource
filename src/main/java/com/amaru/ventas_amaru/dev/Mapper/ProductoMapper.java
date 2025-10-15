package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProveedorResponseDTO;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductoMapper {

    ProductoMapper mapper = Mappers.getMapper(ProductoMapper.class);


    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "proveedorPreferido.nombreProveedor", target = "proveedorPreferido")
    ProductoDTO toDto(Producto producto);

    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "proveedorPreferido.nombreProveedor", target = "proveedorPreferido")
    List<ProductoDTO> toDtoList(List<Producto> productos);

    ProveedorResponseDTO productoToDTO(Producto producto);

    Producto productoToEntity (ProveedorResponseDTO proveedorResponseDTO);
}
