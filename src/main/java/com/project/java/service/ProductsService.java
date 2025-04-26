package com.project.java.service;

import com.project.java.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    List<ProductsDto> getProductByName(String name);
    List<ProductsDto> getAllProducts();
    ProductsDto save(ProductsDto productsDto);
    void delete(int id);
    ProductsDto update(ProductsDto productsDto, Integer id);
}