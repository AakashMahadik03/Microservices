package com.userservice.service;

import java.util.List;

import com.userservice.dto.UserDTO;
import com.userservice.dto.UserResponse;


public interface UserService {

	UserResponse getUserById(int userId);
	
	String createUser(UserDTO user);
	
	List<UserResponse> getAllUser();
}
