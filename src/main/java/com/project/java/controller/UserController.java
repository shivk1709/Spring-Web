package com.project.java.controller;

import com.project.java.dto.UsersDto;
import com.project.java.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/usr")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsersDto user) {
        UsersDto usersDto = userService.saveUser(user);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UsersDto user, @PathVariable int id) {
        UsersDto usersDto = userService.updateUser(id, user);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

}
