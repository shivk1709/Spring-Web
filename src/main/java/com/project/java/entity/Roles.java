package com.project.java.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "ROLES")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<Users> users;

    @Column(name = "CREATED_AT")
    private String created_at;

    @Column(name = "UPDATED_AT")
    private String updated_at;

    @Column(name = "DELETED_AT")
    private String deleted_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
