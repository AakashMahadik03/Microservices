	package com.userservice.model;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import lombok.Getter;
	import lombok.Setter;
	
	@Table(name = "user")
	@Entity
	@Setter
	@Getter
	public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userId;
		
		private String username;
		
		private String email;
		
		private String phone;
	}
