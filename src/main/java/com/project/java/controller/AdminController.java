package com.project.java.controller;

import com.project.java.Utils.SecurityUtils;
import com.project.java.dto.CategoriesDto;
import com.project.java.dto.UsersDto;
import com.project.java.dto.UsersInfo;
import com.project.java.service.AdminService;
import com.project.java.service.CategoriesService;
import jakarta.validation.Valid;
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
    private final CategoriesService categoriesService;

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
    public ResponseEntity<?> saveUser(@RequestBody @Valid UsersDto user) {
        UsersDto usersDto = adminService.saveUser(user);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UsersDto user, @PathVariable int id) {
        UsersDto usersDto = adminService.makeAdmin(id);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PostMapping("/add/category")
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoriesDto categoriesDto) {
        CategoriesDto savedCategory = categoriesService.addCategory(categoriesDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @PutMapping("/update/products/{name}")
    public ResponseEntity<?> updateProductsByCategory(@RequestBody @Valid CategoriesDto categoriesDto, @PathVariable String name) {
        CategoriesDto savedCategory = categoriesService.updateProductsByCategory(categoriesDto, name);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @GetMapping("/all/category")
    public ResponseEntity<?> getAllCategories() {
        List<CategoriesDto> categories = categoriesService.getAllCategories();
        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return ResponseEntity.ok("No Category Found");
    }


    public UsersInfo getUsersInfo(List<UsersDto> users) {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.setTotalUsers(users.size());
        usersInfo.setUsers(users);
        return usersInfo;
    }

    @GetMapping("/{name}")
    public String findUser(@PathVariable String name) {
        return SecurityUtils.getCurrentUsername();
    }


}
