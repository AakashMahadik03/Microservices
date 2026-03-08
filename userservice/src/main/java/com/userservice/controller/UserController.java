package com.userservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.UserDTO;
import com.userservice.dto.UserResponse;
import com.userservice.dto.UserUpdateDTO;
import com.userservice.service.UserService;

import jakarta.validation.Valid;
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
	
	@GetMapping("/get/{userId}")
	public UserResponse testException(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	@PostMapping("/create-user")
	public String createUser(@Valid @RequestBody UserDTO user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/getalluser")
	public List<UserResponse> getAllUser(){
		List<UserResponse> response = userService.getAllUser();
		return response;
	}
	
	@PutMapping("/updateuser/{userId}")
	public String updateUser(@RequestBody UserUpdateDTO response, @PathVariable int userId) {
		return userService.updateUser(response, userId);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public String deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
		
}
