package com.project.java.dto;

import com.project.java.entity.Roles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UsersDto {

    private int id;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits, optionally starting with a '+'")
    private String phoneNumber;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    private String status;

    private String created_at;

    private String updated_at;

    private String deleted_at;

    private Set<String> roless;

    private List<OrdersDto> orderss;

    public void setRoleNames(Set<Roles> roles) {
        if (roles != null) {
            this.roless = roles.stream()
                    .map(Roles::getName)
                    .collect(Collectors.toSet());
        }
    }

}
