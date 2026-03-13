package com.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.client.UserClient;
import com.orderservice.dto.OrderRequestDTO;
import com.orderservice.dto.UserResponse;
import com.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final UserClient userClient;
	
	private final OrderService orderService;
	
	@GetMapping("/test")
	public String testOrder() {
		return "Order is calling user test service --> " + userClient.getUserTest();
	}
	
	@GetMapping("/testexception/{userId}")
	public UserResponse testException(@PathVariable int userId) {
		UserResponse response = userClient.getUserById(userId); 
		return response;
	}
	
	@PostMapping("/create/{userId}")
	public String createOrder(@PathVariable int userId, @RequestBody OrderRequestDTO dto) {
		return orderService.createOrder(userId, dto);
	}

}
