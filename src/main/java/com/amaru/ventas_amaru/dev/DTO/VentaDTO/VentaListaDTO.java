package com.amaru.ventas_amaru.dev.DTO.VentaDTO;

import com.amaru.ventas_amaru.dev.Entity.Cliente;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Usuario;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.Venta;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class VentaListaDTO {
    private Integer idVenta;
    private String mombreCliente;
    private String nombreUsuario;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private String metodoPago;
    private String estadoVenta;
    private String imgUrl;
}
