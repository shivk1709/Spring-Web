package com.project.java.service;

import com.project.java.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    List<ProductsDto> getProductByName(String name);

}
