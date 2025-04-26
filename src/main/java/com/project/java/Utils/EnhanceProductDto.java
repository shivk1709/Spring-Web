package com.project.java.Utils;

import com.project.java.dto.ProductsDto;
import com.project.java.entity.Products;

import java.math.BigDecimal;

public class EnhanceProductDto
{
    public static ProductsDto enhanceProductDto(ProductsDto productsDto, Products products)
    {
        BigDecimal mrp = products.getMrp();
        BigDecimal price = products.getPrice();
        BigDecimal difference = mrp.subtract(price);
        BigDecimal percentage = difference.multiply(BigDecimal.valueOf(100)).divide(mrp, 2, BigDecimal.ROUND_HALF_UP);
        productsDto.setDiscount_percentage(percentage.intValue());
        return productsDto;
    }
}
