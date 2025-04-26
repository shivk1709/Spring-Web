package com.project.java.serviceImpl;

import com.project.java.Utils.DateUtils;
import com.project.java.dao.RolesRepository;
import com.project.java.dao.UsersRepository;
import com.project.java.dto.UsersDto;
import com.project.java.entity.Roles;
import com.project.java.entity.Users;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RolesRepository rolesRepository;

    private static final String INACTIVE = "INACTIVE";
    private static final String ACTIVE = "ACTIVE";
    private static final String USER = "USER";

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UsersDto updateUser(int id, UsersDto userDto) {
        Users fetchedUser = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not Exist"));
        fetchedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        fetchedUser.setUpdated_at(DateUtils.formatDate());
        fetchedUser.setStatus(ACTIVE);

        Users updatedUser = usersRepository.save(fetchedUser);
        return modelMapper.map(updatedUser, UsersDto.class);
    }


    @Override
    @Transactional
    public UsersDto saveUser(UsersDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(ACTIVE);
        user.setCreated_at(DateUtils.formatDate());

        Set<Roles> roles = new HashSet<>();
        Roles fetchedRole = rolesRepository.findByName(USER);
        roles.add(fetchedRole);

        user.setRoles(roles);
        Users savedUser = usersRepository.save(user);
        UsersDto dto = modelMapper.map(savedUser, UsersDto.class);
        dto.setRoleNames(savedUser.getRoles());
        return dto;
    }

    @Override
    public String checkLogin(UsersDto userDto) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(userDto);
        }
        return "Failed";
    }

}

