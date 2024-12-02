package com.project.java.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime created_at;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updated_at;

    @Column(name = "DELETED_AT")
    private LocalDateTime deleted_at;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Categories category;

}
