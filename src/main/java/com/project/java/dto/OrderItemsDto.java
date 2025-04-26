package com.project.java.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemsDto {

    private int id;

    private ProductSummaryDto product;

    private Integer quantity;

    private BigDecimal price;

    private String created_at;

    private String updated_at;

    private String deleted_at;

}
