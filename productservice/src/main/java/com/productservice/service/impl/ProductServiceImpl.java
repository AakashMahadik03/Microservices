package com.productservice.service.impl;

import org.springframework.stereotype.Service;

import com.productservice.dto.OrderRequestDTO;
import com.productservice.dto.ProductResponse;
import com.productservice.exception.NotFoundException;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	@Override
	public ProductResponse getProductForOrder(int productId) {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new NotFoundException("Product :: Product Not Found with id:- "+productId));

		ProductResponse response = convertToDTO(product);
		
		return response;
	}
	
	public ProductResponse convertToDTO(Product product) {
		ProductResponse response = new  ProductResponse();
		response.setName(product.getName());
		response.setPrice(product.getPrice());
		response.setQuantity(product.getQuantity());
		return response;
	}

	@Override
	public String createProduct(ProductResponse response) {
		Product product = new Product();
		product.setName(response.getName());
		product.setPrice(response.getPrice());
		product.setQuantity(response.getQuantity());
		
		productRepository.save(product);
		return "product created sucessfully";
	}

	@Override
	public boolean updateProductCountAfterOrder(OrderRequestDTO dto) {
		
		Product product = productRepository.findById(dto.getProductId()).orElseThrow(
				() -> new NotFoundException("Product :: Product Not Found with id:- "+dto.getProductId()));
		
		int newQuantity = product.getQuantity() - dto.getQuantity();
		product.setQuantity(newQuantity);
		productRepository.save(product);
		return true;
	}

}
