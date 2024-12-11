package com.project.java.controller;

import com.project.java.dao.UsersRepository;
import com.project.java.dto.UsersDto;
import com.project.java.dto.UsersInfo;
import com.project.java.entity.Users;
import com.project.java.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UsersRepository usersRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UsersDto> users = adminService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.ok("No User Found");
        }
        UsersInfo allUsers = getUsersInfo(users);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UsersDto user = adminService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.ok("User Not Found");
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String role) {
        List<UsersDto> users = adminService.getUserByEmail(role);
        if (!users.isEmpty()) {
            UsersInfo allUsers = getUsersInfo(users);
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return ResponseEntity.ok("User Not Found");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getUsersByStatus(@PathVariable String status) {
        List<UsersDto> users = adminService.getUserByStatus(status);
        if (!users.isEmpty()) {
            UsersInfo allUsers = getUsersInfo(users);
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return ResponseEntity.ok("User Not Found");
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<UsersDto> inActiveUser(@PathVariable int id) {
        return new ResponseEntity<>(adminService.inActiveUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsersDto user) {
        UsersDto usersDto = adminService.saveUser(user);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UsersDto user, @PathVariable int id) {
        UsersDto usersDto = adminService.makeAdmin(id);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }


    public UsersInfo getUsersInfo(List<UsersDto> users) {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.setTotalUsers(users.size());
        usersInfo.setUsers(users);
        return usersInfo;
    }

    @GetMapping("/{name}")
    public List<Users> findUser(@PathVariable String name) {
        return usersRepository.findByStatus(name);
    }


}
