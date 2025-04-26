package com.project.java.controller;

import com.project.java.dto.BrandsDto;
import com.project.java.dto.CategoriesDto;
import com.project.java.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandsController
{

    private final BrandService brandService;

    @PostMapping("/save")
    public ResponseEntity<?> addBrand(@Valid @RequestBody BrandsDto brandsDtoRequest)
    {
        BrandsDto brandsDto = brandService.addBrand(brandsDtoRequest);
        return new ResponseEntity<>(brandsDto, HttpStatus.OK);
    }
}
