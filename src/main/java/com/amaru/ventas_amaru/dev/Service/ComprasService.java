package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.ComprasRequest;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.ComprasResponse;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.DetalleComprasRequest;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.DetalleComprasResponse;

import java.util.List;

public interface ComprasService {

    public List<ComprasResponse> listarCompras();

    public List<DetalleComprasResponse> listarDetalleCompras(Integer id);
}
