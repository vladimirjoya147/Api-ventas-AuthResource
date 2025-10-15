package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.VentaDTO.*;

import java.util.List;

public interface VentaService {

    public List<VentaListaDTO> listarVentas();

    public VentaResponse guardarVenta(VentaRequestDTO ventaRequestDTO);

    public VentaDetallesDTO obtenerVentaPorId(Integer idVenta);

    public byte[] generarComprobantePDF(Integer id);
}
