package com.project.java.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductsDto {

    private int id;

    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Product description cannot be empty")
    @Size(max = 500, message = "Product description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Product price cannot be null")
    @Min(value = 0, message = "Product price must be a positive value")
    private Double price;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageUrl;

    @NotNull(message = "Product stock quantity cannot be null")
    @Min(value = 0, message = "Product stock quantity cannot be negative")
    private Integer stockQuantity;

    private String created_at;

    private String updated_at;

    private String deleted_at;


}
