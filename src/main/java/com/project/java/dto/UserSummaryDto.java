package com.project.java.dto;

import lombok.Data;

@Data
public class UserSummaryDto {

    private int id;
    private String username;
    private String email;
    private String created_at;

}
