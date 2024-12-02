package com.project.java.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersInfo {

    private int totalUsers;
    private List<UsersDto> users;

}
