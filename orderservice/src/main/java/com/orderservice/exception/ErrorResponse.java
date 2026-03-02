package com.orderservice.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private int statusCode;
	private String message;
	private LocalDateTime timeStamp;
	
	public ErrorResponse(int statusCode,String message) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.timeStamp = LocalDateTime.now();
	}
}

