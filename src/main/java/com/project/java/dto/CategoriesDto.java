package com.project.java.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoriesDto {

    private int id;

    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Category description cannot be empty")
    @Size(max = 255, message = "Category description cannot exceed 255 characters")
    private String description;

    private List<@Size(max = 255, message = "Each image URL must be 255 characters or less") String> imageUrl;

    private String created_at;

    private String updated_at;

    private String deleted_at;

    private List<@Valid ProductsDto> products;

}
