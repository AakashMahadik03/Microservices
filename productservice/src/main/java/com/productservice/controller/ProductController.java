package com.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.dto.OrderRequestDTO;
import com.productservice.dto.ProductResponse;
import com.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/get/{productId}")
	public ProductResponse getProductForOrder(@PathVariable int productId) {
		return productService.getProductForOrder(productId);
	}
	
	@PostMapping("/create")
	public String createProduct(@RequestBody ProductResponse response) {
		return productService.createProduct(response);
	}
	
	@PutMapping("/updatecount")
	public boolean updateProductCountAfterOrder(@RequestBody OrderRequestDTO dto) {
		return productService.updateProductCountAfterOrder(dto);
	}
}
