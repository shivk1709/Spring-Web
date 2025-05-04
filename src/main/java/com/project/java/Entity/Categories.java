package com.project.java.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "CATEGORIES")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "Category description cannot be empty")
    @Size(max = 255, message = "Category description cannot exceed 255 characters")
    @Column(name = "DESCRIPTION")
    private String description;

    @ElementCollection
    @CollectionTable(name = "category_images", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "IMAGE_URL")
    private List<@Size(max = 255, message = "Image URL too long") String> imageUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    @JsonIgnore
    private Brands brands;

    @Column(name = "CREATED_AT", updatable = false)
    private String created_at;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;

}
