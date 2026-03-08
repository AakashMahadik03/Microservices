package com.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.userservice.dto.UserDTO;
import com.userservice.dto.UserResponse;
import com.userservice.dto.UserUpdateDTO;
import com.userservice.exception.EmailAlreadyExistException;
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

	@Override
	public String createUser(UserDTO user) {
		User create = new User();
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistException("Email "+user.getEmail()+" Already exists in system.");
		}
		create.setEmail(user.getEmail());
		create.setUsername(user.getUsername());
		create.setPhone(user.getPhone());
		
		userRepository.save(create);
		
		return "User created sucessfully!";
	}

	@Override
	public List<UserResponse> getAllUser() {
		List<User> allUsers = userRepository.findAll();
		List<UserResponse> response = allUsers.stream().map(user -> convertToDto(user)).toList();		
		return response;
	}
	
	private static UserResponse convertToDto(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getUserId());
		userResponse.setEmail(user.getEmail());
		userResponse.setName(user.getUsername());
		userResponse.setPhone(user.getPhone());
		return userResponse;
	}

	@Override
	public String updateUser(UserUpdateDTO dto, int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(
						() ->  new UserNotFoundException("User not found with id:- "+ userId));
		user.setUsername(dto.getUsername());
		user.setPhone(dto.getPhone());
		userRepository.save(user);
		return "User updated sucessfully";
	}

	@Override
	public String deleteUser(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(
						() ->  new UserNotFoundException("User not found with id:- "+ userId));
		userRepository.delete(user);
		return "User deleted sucessfully";
	}

}
