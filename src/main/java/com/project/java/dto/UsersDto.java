package com.project.java.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UsersDto {

    private int id;
    private String email;
    private String password;
    private String username;
    private String role;
    private String status;
    private String created_at;
    private String updated_at;
    private String deleted_at;

}
