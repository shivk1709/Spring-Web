package com.project.java.entity;

import jakarta.persistence.*;
import lombok.Data;

//@Data
//@Entity
//@Table(name = "USER_ROLES")
public class UserRoles {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_ID")
    private int user_id;

    @Column(name = "ROLE_ID")
    private int role_id;

}
