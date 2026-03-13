package com.productservice.dto;

import lombok.Data;

@Data
public class ProductResponse {

	private String name;

    private double price;

    private int quantity;
}
