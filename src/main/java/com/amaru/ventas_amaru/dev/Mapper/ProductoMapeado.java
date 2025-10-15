package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ProductoDTO.ProductoRequest;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;

public class ProductoMapeado {

    public static Producto getProducto(ProductoRequest producto, Categoria categoria, Proveedor proveedor) {
        Producto pro = new Producto();
        pro.setCodigoBarra(producto.getCodigoBarra());
        pro.setNombre(producto.getNombre());
        pro.setDescripcion(producto.getDescripcion());
        pro.setCategoria(categoria);
        pro.setProveedorPreferido(proveedor);
        pro.setPrecioCompra(producto.getPrecioCompra());
        pro.setPrecioVenta(producto.getPrecioVenta());
        pro.setStock(producto.getStock());
        pro.setStockMinimo(producto.getStockMinimo());
        pro.setActivo(true);
        pro.setPath(producto.getPath());
        return pro;
    }
}
