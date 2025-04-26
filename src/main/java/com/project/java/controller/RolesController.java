package com.project.java.controller;

import com.project.java.dao.RolesRepository;
import com.project.java.dto.RolesDto;
import com.project.java.entity.Roles;
import com.project.java.service.RolesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RolesController {

    private final RolesService rolesService;
    private final RolesRepository rolesRepository;

    @PostMapping
    public ResponseEntity<?> saveRole(@RequestBody RolesDto role){
        return new ResponseEntity<>(rolesService.saveRole(role), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public Roles findRoles(@PathVariable String name){
        return rolesRepository.findByName(name);
    }

}
