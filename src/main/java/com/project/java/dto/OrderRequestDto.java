package com.project.java.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private Integer userId;
    private List<OrderItemRequestDto> items;

}
