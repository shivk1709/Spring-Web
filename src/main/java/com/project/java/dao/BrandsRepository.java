package com.project.java.dao;

import com.project.java.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brands, Integer>
{
}
