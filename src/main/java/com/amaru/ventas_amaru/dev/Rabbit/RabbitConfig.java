package com.amaru.ventas_amaru.dev.Rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Bean
    public TopicExchange ventasExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue ventasQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding ventasBinding(Queue ventasQueue, TopicExchange ventasExchange) {
        return BindingBuilder.bind(ventasQueue)
                .to(ventasExchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}