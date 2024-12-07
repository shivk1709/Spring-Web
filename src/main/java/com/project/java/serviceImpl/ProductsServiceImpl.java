package com.project.java.serviceImpl;

import com.project.java.dao.ProductsRepo;
import com.project.java.dto.ProductsDto;
import com.project.java.entity.Products;
import com.project.java.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepo productsRepo;
    private final ModelMapper modelMapper;

    public ProductsServiceImpl(ProductsRepo productsRepo, ModelMapper modelMapper) {
        this.productsRepo = productsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductsDto> getProductByName(String name) {
        List<Products> products = productsRepo.findByNameContaining(name);
        return products.stream().map(product -> modelMapper.map(product, ProductsDto.class)).toList();
    }
}
