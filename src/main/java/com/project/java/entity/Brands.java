package com.project.java.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "BRANDS")
public class Brands
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    @Column(name = "NAME", nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "brand_images", joinColumns = @JoinColumn(name = "brand_id"))
    @Column(name = "IMAGE_URL")
    private List<@Size(max = 255, message = "Each image URL must be 255 characters or less") String> imageUrl;

    @OneToMany(mappedBy = "brands", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categories> categories;

    @Column(name = "CREATED_AT", updatable = false)
    private String created_at;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;
    
}
