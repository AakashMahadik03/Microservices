package com.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.UserResponse;
import com.userservice.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@GetMapping("/test")
	public String test() {
		return "User service working...";
	}
	
	@GetMapping("/testexception/{userId}")
	public UserResponse testException(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
}
