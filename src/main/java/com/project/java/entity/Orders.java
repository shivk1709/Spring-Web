package com.project.java.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;

    @Column(name = "TOTALPRICE")
    private BigDecimal totalPrice;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ORDERDATE", updatable = false)
    private String orderDate;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;


}
