package com.project.java.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductsDto {

    private int id;

    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Product description cannot be empty")
    @Size(max = 500, message = "Product description cannot exceed 500 characters")
    private String description;

    @DecimalMin(value = "0.1", message = "Price must be at least 0.1")
    @DecimalMax(value = "99999.99", message = "Price cannot exceed 99999.99")
    @Digits(integer = 5, fraction = 2, message = "Price must have up to 5 digits and 2 decimal places")
    private BigDecimal price = BigDecimal.ZERO;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageUrl;

    @NotNull(message = "Product stock quantity cannot be null")
    @Min(value = 0, message = "Product stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotBlank(message = "Product Flavour cannot be empty")
    @Size(max = 500, message = "Product Flavour cannot exceed 500 characters")
    private String flavour;

    @DecimalMin(value = "0.1", inclusive = true, message = "Weight must be at least 0.1 lbs")
    @DecimalMax(value = "999.9", inclusive = true, message = "Weight cannot exceed 999.9 lbs")
    @Digits(integer = 3, fraction = 2, message = "Weight must have up to 3 digits and 2 decimal place")
    private BigDecimal weight = BigDecimal.ZERO;

    @DecimalMin(value = "0.1", message = "MRP must be at least 0.1")
    @DecimalMax(value = "99999.99", message = "MRP cannot exceed 99999.99")
    @Digits(integer = 5, fraction = 2, message = "MRP must have up to 5 digits and 2 decimal places")
    private BigDecimal mrp = BigDecimal.ZERO;

    private int discount_percentage;

    private String created_at;

    private String updated_at;

    private String deleted_at;


}
