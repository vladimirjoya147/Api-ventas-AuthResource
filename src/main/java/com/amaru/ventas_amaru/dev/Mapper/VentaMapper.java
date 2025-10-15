package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.VentaListaDTO;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.Venta;

public class VentaMapper {
    public static VentaListaDTO toDTO(Venta venta){
        VentaListaDTO dto= new VentaListaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setMombreCliente(venta.getCliente().getNombreCliente());
        dto.setNombreUsuario(venta.getUsuario().getNombreCompleto());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setEstadoVenta(venta.getEstado().name());
        dto.setMetodoPago(venta.getMetodoPago().name());
        dto.setTotalVenta(venta.getTotalVenta());
        return dto;
    }
}
