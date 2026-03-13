package com.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderservice.dto.OrderRequestDTO;
import com.orderservice.dto.ProductResponse;

@FeignClient("PRODUCTSERVICE")
public interface ProductClient {

		@GetMapping("/products/get/{productId}")
		ProductResponse getProductForOrder(@PathVariable int productId);

		@PutMapping("/products/updatecount")
		boolean updateProductCountAfterOrder(@RequestBody OrderRequestDTO dto);
}
