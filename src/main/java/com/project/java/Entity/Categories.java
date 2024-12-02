package com.project.java.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CATEGORIES")
@Data
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime created_at;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updated_at;

    @Column(name = "DELETED_AT")
    private LocalDateTime deleted_at;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Products> products;

}
