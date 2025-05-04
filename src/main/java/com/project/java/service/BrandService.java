package com.project.java.service;

import com.project.java.dto.BrandsDto;

import java.util.List;

public interface BrandService
{
    BrandsDto addBrand(BrandsDto brandsDto);
    List<BrandsDto> getAllBrands();
}
