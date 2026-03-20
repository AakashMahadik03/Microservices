package com.paymentservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentEventConsumer {
	
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@KafkaListener(topics = "stock-reserved", groupId = "payment-group")
	public void processPayment(OrderCreatedEvent event) {

	    boolean paymentSuccess = processPaymentLogic(event);

	    if(paymentSuccess) {

	        kafkaTemplate.send("payment-success", event);

	    }else {
	    	
	        kafkaTemplate.send("payment-failed", event);
	    	 
	    }

	}
	
	private boolean processPaymentLogic(OrderCreatedEvent event) {
		if(event.getQuantity()>10) {
			return false;
		}
		
		return true;
	}
	
}
