package com.orderservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreated(OrderCreatedEvent event) {

        kafkaTemplate.send("order-created", event);

        System.out.println("Order event sent: " + event);
    }
    
}
