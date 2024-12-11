package com.project.java.dao;

import com.project.java.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {

//    List<Users> findByRole(String role);
    List<Users> findByStatus(String status);
    Users findByUsername(String username);

    @Query("SELECT u FROM Users u JOIN FETCH u.roles WHERE u.username = :username")
    Users findUserWithRoles(@Param("username") String username);

}
