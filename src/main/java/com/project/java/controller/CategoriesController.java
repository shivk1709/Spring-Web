package com.project.java.controller;

import com.project.java.dto.CategoriesDto;
import com.project.java.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCategoriesByName(@PathVariable String name) {
        CategoriesDto categories = categoriesService.getCategoriesByName(name);
        if (categories != null) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return ResponseEntity.ok("No Result Found");
    }

}
