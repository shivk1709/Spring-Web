package com.project.java.dto;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class ProductsDto {

    private int id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

}
