package com.orderservice.service.impl;

import org.springframework.stereotype.Service;

import com.common.event.OrderCreatedEvent;
import com.orderservice.client.PaymentClient;
import com.orderservice.client.ProductClient;
import com.orderservice.client.UserClient;
import com.orderservice.dto.OrderRequestDTO;
import com.orderservice.dto.PaymentRequestDTO;
import com.orderservice.dto.ProductResponse;
import com.orderservice.dto.UserResponse;
import com.orderservice.exception.InsufficientResourceException;
import com.orderservice.exception.NotFoundException;
import com.orderservice.model.Order;
import com.orderservice.model.Status;
import com.orderservice.producer.OrderEventProducer;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

	private final UserClient userClient;
	
	private final ProductClient productClient;
	
	private final PaymentClient paymentClient;
	
	private final OrderRepository orderRepository;
	
	private final OrderEventProducer eventProducer;
	
	@Override
	public String createOrder(int userId, OrderRequestDTO dto)  {
		
		String response;
		
		UserResponse userResponse = userClient.getUserById(userId);

		if (userResponse == null) {
			throw new NotFoundException("Order :: User Not Found with id:- "+userId);
		}
		
		ProductResponse productResponse = productClient.getProductForOrder(dto.getProductId());
		
		if (productResponse == null) {
			throw new NotFoundException("Order :: Product Not Found with id:- "+ dto.getProductId());
		}
		
		Order newOrder = new Order();
		newOrder.setProductId(dto.getProductId());
		newOrder.setQuantity(dto.getQuantity());
		newOrder.setProductName(productResponse.getName());
		newOrder.setStatus(Status.Inprocess);
		double totalAmount = dto.getQuantity() * productResponse.getPrice();
		newOrder.setTotalAmmount(totalAmount);
		newOrder.setUserId(userId);
		Order order = orderRepository.save(newOrder);
		
		OrderCreatedEvent event = new OrderCreatedEvent(
	            order.getOrderId(),
	            order.getProductId(),
	            order.getQuantity(),
	            order.getUserId()
	    );
		
		eventProducer.publishOrderCreated(event);
		
		return "Order created and event published";
	}

}
