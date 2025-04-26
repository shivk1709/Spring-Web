package com.project.java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
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

    @DecimalMin(value = "0.1", message = "Price must be at least 0.1")
    @DecimalMax(value = "99999.99", message = "Price cannot exceed 99999.99")
    @Digits(integer = 5, fraction = 2, message = "Price must have up to 5 digits and 2 decimal places")
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull(message = "Product stock quantity cannot be null")
    @Min(value = 0, message = "Product stock quantity cannot be negative")
    @Column(name = "STOCKQUANTITY")
    private Integer stockQuantity;

    @NotBlank(message = "Product Flavour cannot be empty")
    @Size(max = 500, message = "Product Flavour cannot exceed 500 characters")
    @Column(name = "FLAVOUR")
    private String flavour;

    @DecimalMin(value = "0.1", inclusive = true, message = "Weight must be at least 0.1 lbs")
    @DecimalMax(value = "999.9", inclusive = true, message = "Weight cannot exceed 999.9 lbs")
    @Digits(integer = 3, fraction = 2, message = "Weight must have up to 3 digits and 2 decimal place")
    @Column(name = "WEIGHT")
    private BigDecimal weight = BigDecimal.ZERO;

    @DecimalMin(value = "0.1", message = "MRP must be at least 0.1")
    @DecimalMax(value = "99999.99", message = "MRP cannot exceed 99999.99")
    @Digits(integer = 5, fraction = 2, message = "MRP must have up to 5 digits and 2 decimal places")
    @Column(name = "MRP")
    private BigDecimal mrp = BigDecimal.ZERO;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<@Size(max = 255, message = "Image URL too long") String> imageUrls;

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
