package com.project.java.service;

import com.project.java.dto.UsersDto;

public interface UserService {

    UsersDto updateUser(int id, UsersDto user);
    UsersDto saveUser(UsersDto user);

}
