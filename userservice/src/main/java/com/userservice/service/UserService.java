package com.userservice.service;

import java.util.List;

import com.userservice.dto.UserDTO;
import com.userservice.dto.UserResponse;
import com.userservice.dto.UserUpdateDTO;


public interface UserService {

	UserResponse getUserById(int userId);
	
	String createUser(UserDTO user);
	
	List<UserResponse> getAllUser();
	
	String updateUser(UserUpdateDTO dto, int userId);
	
	String deleteUser(int userId);
	
}
