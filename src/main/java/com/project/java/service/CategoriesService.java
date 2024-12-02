package com.project.java.service;

import com.project.java.dto.CategoriesDto;
import java.util.List;

public interface CategoriesService {

    CategoriesDto getCategoriesByName(String name);
    List<CategoriesDto> getAllCategories();
}
