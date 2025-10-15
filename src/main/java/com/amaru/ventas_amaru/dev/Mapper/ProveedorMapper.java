package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProvedorRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProveedorResponseDTO;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;

public class ProveedorMapper {

    public static Proveedor proDtoToEntity (ProvedorRequestDTO prov){
        Proveedor pro = new Proveedor();
        pro.setIdProveedor(prov.getIdProveedor());
        pro.setNombreProveedor(prov.getNombreProveedor());
        pro.setRuc(prov.getRuc());
        pro.setTelefono(prov.getTelefono());
        pro.setEmail(prov.getEmail());
        pro.setDireccion(prov.getDireccion());
        pro.setActivo(true);
        return pro;
    }

    public static ProveedorResponseDTO provToDTO(Proveedor prov){
        ProveedorResponseDTO res = new ProveedorResponseDTO();
        res.setIdProveedor(prov.getIdProveedor());
        res.setNombreProveedor(prov.getNombreProveedor());
        res.setRuc(prov.getRuc());
        res.setTelefono(prov.getTelefono());
        res.setDireccion(prov.getDireccion());
        res.setEmail(prov.getEmail());
        res.setActivo(prov.getActivo());
        return res;
    }
}
