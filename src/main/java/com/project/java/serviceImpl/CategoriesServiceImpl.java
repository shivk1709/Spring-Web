package com.project.java.serviceImpl;

import com.project.java.Utils.DateUtils;
import com.project.java.Utils.EnhanceProductDto;
import com.project.java.dao.CategoriesRepository;
import com.project.java.dto.CategoriesDto;
import com.project.java.dto.ProductsDto;
import com.project.java.entity.Categories;
import com.project.java.entity.Products;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService
{

    private final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoriesDto getCategoriesByName(String name)
    {
        Categories category = categoriesRepository.findByName(name);
        if (category != null)
        {
            return modelMapper.map(category, CategoriesDto.class);
        }
        return null;
    }

    @Override
    public List<CategoriesDto> getAllCategories()
    {
        List<Categories> categories = categoriesRepository.findAll();

        return categories.stream().map(category ->
        {
            var categoryDto = modelMapper.map(category, CategoriesDto.class);

            List<ProductsDto> enhancedProducts = new ArrayList<>();
            List<Products> originalProducts = category.getProducts();
            List<ProductsDto> productDtos = categoryDto.getProducts();

            for (int i = 0; i < productDtos.size(); i++)
            {
                ProductsDto enhanced = EnhanceProductDto.enhanceProductDto(productDtos.get(i), originalProducts.get(i));
                enhancedProducts.add(enhanced);
            }

            categoryDto.setProducts(enhancedProducts);
            return categoryDto;
        }).toList();
    }

    @Override
    public CategoriesDto addCategory(CategoriesDto categoriesDto)
    {
        Categories category = modelMapper.map(categoriesDto, Categories.class);
        category.setCreated_at(DateUtils.formatDate());
        var products = category.getProducts();
        products.forEach(product ->
        {
            product.setCreated_at(DateUtils.formatDate());
            product.setCategory(category);
        });
        Categories savedCategory = categoriesRepository.save(category);
        return modelMapper.map(savedCategory, CategoriesDto.class);
    }

    @Override
    public CategoriesDto updateProductsByCategory(CategoriesDto categoriesDto, String name)
    {
        Categories category = categoriesRepository.findByName(name);
        if (category == null)
        {
            throw new ResourceNotFoundException("Category not found");
        }

        category.setName(categoriesDto.getName());
        category.setDescription(categoriesDto.getDescription());
        category.setUpdated_at(DateUtils.formatDate());

        List<ProductsDto> dtoList = categoriesDto.getProducts();

        for (ProductsDto productDto : dtoList)
        {
            // If the product already exists, update it. Otherwise, create a new product.
            Products existingProduct = category.getProducts().stream()
                    .filter(product -> product.getName().equals(productDto.getName()))  // Assumption: Unique name
                    .findFirst()
                    .orElse(null);

            if (existingProduct != null)
            {
                // Update the existing product
                existingProduct.setDescription(productDto.getDescription());
                existingProduct.setUpdated_at(DateUtils.formatDate());
            } else
            {
                Products newProduct = modelMapper.map(productDto, Products.class);
                newProduct.setCategory(category);  // Set the category for the new product
                newProduct.setUpdated_at(DateUtils.formatDate());

                category.getProducts().add(newProduct);
            }
        }

        category.getProducts().removeIf(product ->
                dtoList.stream().noneMatch(dto -> dto.getName().equals(product.getName()))
        );

        Categories updatedCategory = categoriesRepository.save(category);

        return modelMapper.map(updatedCategory, CategoriesDto.class);
    }

}
