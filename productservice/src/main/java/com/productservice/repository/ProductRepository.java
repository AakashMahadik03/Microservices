package com.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Transactional
	@Modifying
	@Query("""
	UPDATE Product p
	SET p.quantity = p.quantity - :quantity
	WHERE p.productId = :productId
	AND p.quantity >= :quantity
	""")
	int reserveStock(
	        @Param("productId") int productId,
	        @Param("quantity") int quantity
	);
	
}
