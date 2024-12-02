package com.project.java.serviceImpl;

import com.project.java.entity.Categories;
import com.project.java.dao.CategoriesRepository;
import com.project.java.dto.CategoriesDto;
import com.project.java.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoriesDto getCategoriesByName(String name) {
        Categories category = categoriesRepository.findByName(name);
        if (category != null) {
            return modelMapper.map(category, CategoriesDto.class);
        }
        return null;
    }

    @Override
    public List<CategoriesDto> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoriesDto.class)).toList();
    }

}
