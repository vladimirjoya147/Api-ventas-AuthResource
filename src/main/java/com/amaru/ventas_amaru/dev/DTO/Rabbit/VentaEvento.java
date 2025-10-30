package com.amaru.ventas_amaru.dev.DTO.Rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class VentaEvento implements Serializable {
    private BigDecimal montoTotal;
    private String cliente;
    private String fecha;

    public VentaEvento(BigDecimal montoTotal, String cliente, String fecha) {
        this.montoTotal = montoTotal;
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public VentaEvento() {
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "VentaEvento{" +
                "montoTotal=" + montoTotal +
                ", cliente='" + cliente + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
