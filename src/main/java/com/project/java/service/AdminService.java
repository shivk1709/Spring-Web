package com.project.java.service;

import com.project.java.dto.UsersDto;
import org.apache.catalina.User;

import java.util.List;

public interface AdminService {

    List<UsersDto> getAllUsers();
    UsersDto getUserById(int id);
    List<UsersDto> getUserByEmail(String role);
    List<UsersDto> getUserByStatus(String status);
    UsersDto inActiveUser(int id);
    UsersDto updateUser(int id, UsersDto usersDto);
    UsersDto saveUser(UsersDto user);
}
