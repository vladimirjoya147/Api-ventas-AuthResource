package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.PageResponse;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoDTO;
import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.*;

public interface ProductoService {

    public PageResponse<ProductoDTO> listarProducto(Pageable pageable);

    public ProductoDTO productoActivar (Integer id, Boolean activo);

    public ProductoDTO productoActualizar (ProductoDTO productoDTO);

    public PageResponse<ProductoDTO> listarPorNombreCodigo (String filtro, Pageable pageable);

    public ProductoDTO guardarProducto (ProductoRequest productoRequest);

    public ProductoDTO buscarPorCodigo (String codigo);
}
