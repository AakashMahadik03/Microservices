package com.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderservice.dto.UserResponse;

@FeignClient("USERSERVICE")
public interface UserClient {

	@GetMapping("/users/test")
	String getUserTest();
	
	@GetMapping("/users/testexception/{userId}")
	UserResponse getUserTestException(@PathVariable int userId);
}
