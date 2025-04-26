package com.project.java.controller;

import com.project.java.dto.CategoriesDto;
import com.project.java.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
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

    @GetMapping()
    public ResponseEntity<?> getAllCategories()
    {
        List<CategoriesDto> categories = categoriesService.getAllCategories();
        if (!categories.isEmpty())
        {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return ResponseEntity.ok("No Category Found");
    }


}
