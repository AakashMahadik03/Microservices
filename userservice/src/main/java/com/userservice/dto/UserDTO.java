package com.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

	private String username;
	
	@NotBlank( message = "Email is required")
	private String email;
	
	private String phone;
}
