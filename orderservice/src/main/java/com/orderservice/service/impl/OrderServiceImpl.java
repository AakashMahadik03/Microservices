package com.orderservice.service.impl;

import org.springframework.stereotype.Service;

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
		
		if(dto.getQuantity() > productResponse.getQuantity()) {
			throw new InsufficientResourceException("Order :: Order Quantity is to large to serve now!!");
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
		
		PaymentRequestDTO paymentDTO = new PaymentRequestDTO();
		
		paymentDTO.setOrderId(order.getOrderId());
		paymentDTO.setTotalAmmount(order.getTotalAmmount());
		
		if(paymentClient.paymentOfOrder(paymentDTO)) {
			response = "Order completed sucessfully";
			productClient.updateProductCountAfterOrder(dto);
			order.setStatus(Status.Sucess);
		}else {
			response = "Order failed due to payment failure";
			order.setStatus(Status.Failed);
		}
		orderRepository.save(order);
		
		return response;
	}

}
