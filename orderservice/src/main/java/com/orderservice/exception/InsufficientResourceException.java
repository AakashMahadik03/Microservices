package com.orderservice.exception;

public class InsufficientResourceException extends RuntimeException{

	public InsufficientResourceException(String msg) {
		super(msg);
	}
}
