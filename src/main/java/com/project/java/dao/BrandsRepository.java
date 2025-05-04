package com.project.java.dao;

import com.project.java.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandsRepository extends JpaRepository<Brands, Integer>
{
    Optional<Brands> findByName(String name);
}
