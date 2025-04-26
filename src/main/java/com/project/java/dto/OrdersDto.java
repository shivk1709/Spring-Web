package com.project.java.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersDto {

    private int id;

    private BigDecimal totalPrice;

    private String status;

    private String orderDate;

    private UserSummaryDto user;

    private List<OrderItemsDto> orderItems;

    private String updated_at;

    private String deleted_at;

}
