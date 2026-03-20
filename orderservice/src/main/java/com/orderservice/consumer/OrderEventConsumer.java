package com.orderservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.common.event.OrderCreatedEvent;
import com.orderservice.model.Order;
import com.orderservice.model.Status;
import com.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderEventConsumer {

	private final OrderRepository orderRepository;
	
	@KafkaListener(topics = "payment-success", groupId = "order-group")
	public void confirmOrder(OrderCreatedEvent event) {

	    Order order = orderRepository.findById(event.getOrderId()).get();

	    order.setStatus(Status.Sucess);

	    orderRepository.save(order);

	}
	
	@KafkaListener(topics = "payment-failed", groupId = "order-group")
	public void failedOrder(OrderCreatedEvent event) {

	    Order order = orderRepository.findById(event.getOrderId()).get();

	    order.setStatus(Status.Failed);

	    orderRepository.save(order);

	}
}
