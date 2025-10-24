package com.amaru.ventas_amaru.dev.Feign;

import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.ComprasRequest;
import com.amaru.ventas_amaru.dev.DTO.ComprasDTO.DetalleComprasRequest;
import com.amaru.ventas_amaru.dev.Security.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "report-service", configuration = FeignClientConfig.class)
public interface ComprasFeignCLient {

    @GetMapping("/compras")
    List<ComprasRequest> listarCompras();

    @GetMapping("/detalle/{id}")
    List<DetalleComprasRequest> listarDetalleCompras(@PathVariable Integer id);

}
