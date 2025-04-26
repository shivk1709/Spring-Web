package com.project.java.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ORDERITEMS")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Products product;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CREATED_AT", updatable = false)
    private String created_at;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;

}
