package com.project.java.controller;

import com.project.java.dao.UsersRepository;
import com.project.java.dto.UsersDto;
import com.project.java.entity.Users;
import com.project.java.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/usr")
public class UserController {

    private final UserService userService;
    private final UsersRepository usersRepository;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UsersDto user) {
        UsersDto usersDto = userService.saveUser(user);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UsersDto user, @PathVariable int id) {
        UsersDto usersDto = userService.updateUser(id, user);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestBody @Valid UsersDto user) {
        Set<String> roles = new HashSet<>(Set.of("USER"));
        user.setRoless(roles);
        return new ResponseEntity<>(userService.checkLogin(user), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public List<Users> findUser(@PathVariable String name) {
        return usersRepository.findByStatus(name);
    }

}
