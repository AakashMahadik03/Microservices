package com.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {

	private int productId;
	
	private String productName;
	
	private int quantity;
}
