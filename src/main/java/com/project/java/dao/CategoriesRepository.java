package com.project.java.dao;


import com.project.java.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    Categories findByName(String name);

}
