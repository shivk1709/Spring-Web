package com.project.java.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
public class RolesDto {

    private int id;

    private String name;

    @JsonIgnore
    private Set<UsersDto> users;

    private String created_at;

    private String updated_at;

    private String deleted_at;
}
