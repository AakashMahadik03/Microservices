package com.productservice.service;

import com.productservice.dto.OrderRequestDTO;
import com.productservice.dto.ProductResponse;

public interface ProductService {

	ProductResponse getProductForOrder(int productId);
	
	String createProduct(ProductResponse response);
	
	boolean updateProductCountAfterOrder(OrderRequestDTO dto);
	
}
