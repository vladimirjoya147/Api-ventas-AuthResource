package com.amaru.ventas_amaru.dev.Rabbit;


import com.amaru.ventas_amaru.dev.DTO.Rabbit.VentaEvento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;


    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEventoVenta(BigDecimal montoTotal, String cliente, String fecha) {
        VentaEvento evento = new VentaEvento(montoTotal, cliente, fecha);
        rabbitTemplate.convertAndSend(exchange, routingKey, evento);
        System.out.println("Enviado venta: " + evento);
    }
}



