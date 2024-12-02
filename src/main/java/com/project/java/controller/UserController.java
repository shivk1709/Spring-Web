package com.project.java.controller;

import com.project.java.dto.UsersDto;
import com.project.java.dto.UsersInfo;
import com.project.java.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UsersDto> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.ok("No User Found");
        }
        UsersInfo allUsers = getUsersInfo(users);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UsersDto user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.ok("User Not Found");
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String role) {
        List<UsersDto> users = userService.getUserByEmail(role);
        if (!users.isEmpty()) {
            UsersInfo allUsers = getUsersInfo(users);
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return ResponseEntity.ok("User Not Found");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getUsersByStatus(@PathVariable String status) {
        List<UsersDto> users = userService.getUserByStatus(status);
        if (!users.isEmpty()) {
            UsersInfo allUsers = getUsersInfo(users);
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return ResponseEntity.ok("User Not Found");
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<UsersDto> inActiveUser(@PathVariable int id) {
        return new ResponseEntity<>(userService.inActiveUser(id), HttpStatus.OK);
    }

    public UsersInfo getUsersInfo(List<UsersDto> users) {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.setTotalUsers(users.size());
        usersInfo.setUsers(users);
        return usersInfo;
    }

}
