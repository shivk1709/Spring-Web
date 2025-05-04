package com.project.java.controller;

import com.project.java.dto.BrandsDto;
import com.project.java.dto.CategoriesDto;
import com.project.java.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
@CrossOrigin("*")
public class BrandsController
{

    private final BrandService brandService;

    @PostMapping("/save")
    public ResponseEntity<?> addBrand(@Valid @RequestBody BrandsDto brandsDtoRequest)
    {
        BrandsDto brandsDto = brandService.addBrand(brandsDtoRequest);
        return new ResponseEntity<>(brandsDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategories()
    {
        List<BrandsDto> brands = brandService.getAllBrands();
        if (!brands.isEmpty())
        {
            return new ResponseEntity<>(brands, HttpStatus.OK);
        }
        return ResponseEntity.ok("No Brand Found");
    }

}
