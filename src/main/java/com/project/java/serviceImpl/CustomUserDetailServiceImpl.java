package com.project.java.serviceImpl;

import com.project.java.dao.UsersRepository;
import com.project.java.entity.Roles;
import com.project.java.entity.Users;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users byUsername = usersRepository.findByUsername(username);
        if (Objects.isNull(byUsername)) {
            throw new UsernameNotFoundException("User Not Found");
        }
        String[] roleNames = byUsername.getRoles().stream()
                .map(Roles::getName)
                .toArray(String[]::new);
        return User
                .builder()
                .username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .roles(roleNames)
                .build();
    }
}

