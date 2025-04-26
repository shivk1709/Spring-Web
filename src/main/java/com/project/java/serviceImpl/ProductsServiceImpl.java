package com.project.java.serviceImpl;

import com.project.java.Utils.EnhanceProductDto;
import com.project.java.dao.ProductsRepository;
import com.project.java.dto.ProductsDto;
import com.project.java.entity.Products;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService
{

    private final ProductsRepository productsRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductsDto> getProductByName(String name)
    {
        List<Products> products = productsRepo.findByNameContaining(name.toLowerCase());
        return products.stream().map(product ->
        {
            var productDto = modelMapper.map(product, ProductsDto.class);
            return EnhanceProductDto.enhanceProductDto(productDto, product);
        }).toList();
    }

    @Override
    public List<ProductsDto> getAllProducts()
    {
        List<Products> products = productsRepo.findAll();
        return products.stream().map(product -> {
           var productDto = modelMapper.map(product, ProductsDto.class);
           return EnhanceProductDto.enhanceProductDto(productDto, product);
        }).toList();
    }

    @Override
    public ProductsDto save(ProductsDto productsDto)
    {
        Products products = modelMapper.map(productsDto, Products.class);
        var savedProduct = productsRepo.save(products);
        return modelMapper.map(savedProduct, ProductsDto.class);
    }

    @Override
    public void delete(int id)
    {
        if (productsRepo.existsById(id) && id != 0)
        {
            productsRepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Id does not Exist");
        }
    }

    @Override
    public ProductsDto update(ProductsDto productsDto, Integer id)
    {
        Products products = productsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found"));
        products.setName(productsDto.getName());
        products.setDescription(productsDto.getDescription());
        products.setStockQuantity(productsDto.getStockQuantity());
        Products saved = productsRepo.save(products);
        return modelMapper.map(saved, ProductsDto.class);
    }



}
