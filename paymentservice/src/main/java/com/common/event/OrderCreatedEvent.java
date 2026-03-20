package com.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderCreatedEvent {

    private int orderId;
    private int productId;
    private Integer quantity;
    private int userId;

}