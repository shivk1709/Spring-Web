package com.project.java.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoriesDto {

    private int id;
    private String name;
    private String description;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private List<ProductsDto> products;

}
