package com.userservice.service;

import com.userservice.dto.UserResponse;


public interface UserService {

	UserResponse getUserById(int userId);
	
}
