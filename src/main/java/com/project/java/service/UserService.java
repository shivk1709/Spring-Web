package com.project.java.service;

import com.project.java.dto.UsersDto;

import java.util.List;

public interface UserService {

    List<UsersDto> getAllUsers();
    UsersDto getUserById(int id);
    List<UsersDto> getUserByEmail(String role);
    List<UsersDto> getUserByStatus(String status);
    UsersDto inActiveUser(int id);
}
