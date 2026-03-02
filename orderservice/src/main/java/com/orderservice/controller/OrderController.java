package com.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.client.UserClient;
import com.orderservice.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final UserClient userClient;
	
	@GetMapping("/test")
	public String testOrder() {
		return "Order is calling user test service --> " + userClient.getUserTest();
	}
	
	@GetMapping("/testexception/{userId}")
	public UserResponse testException(@PathVariable int userId) {
		UserResponse response = userClient.getUserTestException(userId); 
		return response;
	}

}
