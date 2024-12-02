package com.project.java.dao;

import com.project.java.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findByRole(String role);
    List<Users> findByStatus(String status);

}
