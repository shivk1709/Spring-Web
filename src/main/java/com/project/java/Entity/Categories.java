package com.project.java.entity;

import jakarta.persistence.*;
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

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", updatable = false)
    private String created_at;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Products> products;

}
