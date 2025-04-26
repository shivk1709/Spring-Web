package com.project.java.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class BrandsDto
{
    private int id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    @Column(name = "NAME")
    private String name;

    private List<@Size(max = 255, message = "Each image URL must be 255 characters or less") String> imageUrl;

    List<CategoriesDto> categories;

}
