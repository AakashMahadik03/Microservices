package com.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {

	private int orderId;
	
	private double totalAmmount;
	
}
