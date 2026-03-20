package com.productservice.consumer;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.event.OrderCreatedEvent;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderEventConsumer {
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	private final ProductRepository productRepository;

	@Transactional
	@KafkaListener(topics = "order-created", groupId = "product-group")
	public void consumeOrderCreated(OrderCreatedEvent event) {

	    try {

	        System.out.println("EVENT RECEIVED IN PRODUCT SERVICE: " + event);

	        Optional<Product> productOpt = productRepository.findById(event.getProductId());

	        if(productOpt.isEmpty()) {
	            kafkaTemplate.send("stock-failed", event);
	            return;
	        }

	        int updatedRows = productRepository.reserveStock(
	                event.getProductId(),
	                event.getQuantity()
	        );
	        System.out.println("updatedRows count:- "+updatedRows);

	        if(updatedRows > 0) {
	        	System.out.println("stock-reserved");
	            kafkaTemplate.send("stock-reserved", event);

	        } else {
	        	System.out.println("stock-failed");
	            kafkaTemplate.send("stock-failed", event);

	        }

	    } catch (Exception e) {

	        System.out.println("Error processing event: " + e.getMessage());

	    }
	}
}
