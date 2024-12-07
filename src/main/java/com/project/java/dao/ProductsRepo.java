package com.project.java.dao;

import com.project.java.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Products, Integer> {


    List<Products> findByNameContaining(String name);

}
