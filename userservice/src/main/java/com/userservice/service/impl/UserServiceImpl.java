package com.userservice.service.impl;

import org.springframework.stereotype.Service;

import com.userservice.dto.UserResponse;
import com.userservice.exception.UserNotFoundException;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	@Override
	public UserResponse getUserById(int userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() ->  new UserNotFoundException("User not found with id:- "+userId));
		UserResponse userResponse = new UserResponse();
		if(user!=null) {
			userResponse.setId(user.getUserId());
			userResponse.setName(user.getUsername());
			userResponse.setEmail(user.getEmail());
		}
		return userResponse;		
	}

}
