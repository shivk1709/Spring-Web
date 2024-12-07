package com.project.java.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductsDto {

    private int id;
    private String name;
    private String description;
    private String created_at;
    private String updated_at;
    private String deleted_at;

}
