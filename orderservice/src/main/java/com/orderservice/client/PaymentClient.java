package com.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderservice.dto.PaymentRequestDTO;


@FeignClient("PAYMENTSERVICE")
public interface PaymentClient {

	@PostMapping("/payments/processpayment")
	boolean paymentOfOrder(@RequestBody PaymentRequestDTO dto);
}
