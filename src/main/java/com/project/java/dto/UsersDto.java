package com.project.java.dto;

import com.project.java.entity.Roles;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UsersDto {

    private int id;
    private String email;
    private String password;
    private String username;
    private String status;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private Set<String> roless;

    public void setRoleNames(Set<Roles> roles) {
        if (roles != null) {
            this.roless = roles.stream()
                    .map(Roles::getName)
                    .collect(Collectors.toSet());
        }
    }

}
