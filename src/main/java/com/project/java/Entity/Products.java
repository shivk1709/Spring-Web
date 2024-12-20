package com.project.java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "Product description cannot be empty")
    @Size(max = 500, message = "Product description cannot exceed 500 characters")
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull(message = "Product price cannot be null")
    @Min(value = 0, message = "Product price must be a positive value")
    @Column(name = "PRICE")
    private Double price;

    @NotNull(message = "Product stock quantity cannot be null")
    @Min(value = 0, message = "Product stock quantity cannot be negative")
    @Column(name = "STOCKQUANTITY")
    private Integer stockQuantity;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Lob
    @Column(name = "IMAGE")
    private String imageUrl;

    @Column(name = "CREATED_AT", updatable = false)
    private String created_at;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Categories category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orders;

}
