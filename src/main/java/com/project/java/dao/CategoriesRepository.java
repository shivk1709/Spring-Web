package com.project.java.dao;


import com.project.java.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    Categories findByName(String name);

}
