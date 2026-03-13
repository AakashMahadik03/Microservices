package com.paymentservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.dto.PaymentRequestDTO;

@RequestMapping("/payments")
@RestController
public class PaymentController {

	@PostMapping("processpayment")
	public boolean paymentOfOrder(@RequestBody PaymentRequestDTO dto) {
		/* Here we do the payment processing things in payments*/
		/* Just creating falls logic to act fauiler as well*/
		if(dto.getTotalAmmount()>100000) {
			return false;
		}
		return true;
	}
}
