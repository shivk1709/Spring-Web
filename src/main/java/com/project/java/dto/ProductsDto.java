package com.project.java.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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
    private List<@Size(max = 255, message = "Each image URL must be 255 characters or less") String> imageUrl;

    @NotNull(message = "Product stock quantity cannot be null")
    @Min(value = 0, message = "Product stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotBlank(message = "Product Flavour cannot be empty")
    @Size(max = 500, message = "Product Flavour cannot exceed 500 characters")
    private String flavour;

    @Digits(integer=7, fraction=2, message="Must have up to 2 decimal places")
    private BigDecimal weight;

    @NotNull(message = "Weight unit must be specified")
    private WeightUnit weightUnit;

    @DecimalMin(value = "0.1", message = "MRP must be at least 0.1")
    @DecimalMax(value = "99999.99", message = "MRP cannot exceed 99999.99")
    @Digits(integer = 5, fraction = 2, message = "MRP must have up to 5 digits and 2 decimal places")
    private BigDecimal mrp = BigDecimal.ZERO;

    private int discount_percentage;

    private String created_at;

    private String updated_at;

    private String deleted_at;


}
