package com.orderservice.service;

import com.orderservice.dto.OrderRequestDTO;

public interface OrderService {

	String createOrder(int userId, OrderRequestDTO dto);
	
}
