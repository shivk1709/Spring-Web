package com.project.java.dao;

import com.project.java.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles findByName(String name);

}
