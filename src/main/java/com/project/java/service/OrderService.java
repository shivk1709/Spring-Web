package com.project.java.service;

import com.project.java.dto.OrderItemsDto;
import com.project.java.dto.OrderRequestDto;
import com.project.java.dto.OrdersDto;
import com.project.java.entity.Orders;

public interface OrderService {

    OrdersDto placeOrder(OrderRequestDto orderRequestDto);

}
