package com.project.java.serviceImpl;

import com.project.java.Util.DateUtil;
import com.project.java.dao.RolesRepository;
import com.project.java.entity.Roles;
import com.project.java.entity.Users;
import com.project.java.dao.UsersRepository;
import com.project.java.dto.UsersDto;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.AdminService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String INACTIVE = "INACTIVE";
    private static final String ACTIVE = "ACTIVE";
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final RolesRepository rolesRepository;


    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(user -> {
            UsersDto userDto = modelMapper.map(user, UsersDto.class);
            userDto.setRoleNames(user.getRoles());
            return userDto;
        }).toList();
    }

    @Override
    public UsersDto getUserById(int id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found for Id - " + id));
        UsersDto mappedDto = modelMapper.map(user, UsersDto.class);
        mappedDto.setRoleNames(user.getRoles());
        return mappedDto;
    }

    @Override
    public List<UsersDto> getUserByEmail(String role) {
//        List<Users> users = usersRepository.findByRole(role);
//        return users.stream().map(user -> modelMapper.map(user, UsersDto.class)).toList();
        return List.of();
    }

    @Override
    public List<UsersDto> getUserByStatus(String status) {
        List<Users> users = usersRepository.findByStatus(status);
        return users.stream().map(user -> {
            UsersDto userDto = modelMapper.map(user, UsersDto.class);
            userDto.setRoleNames(user.getRoles());
            return userDto;
        }).toList();
    }

    @Override
    public UsersDto inActiveUser(int id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found for Id - " + id));
        user.setStatus(INACTIVE);
        user.setUpdated_at(DateUtil.formatDate());
        user.setDeleted_at(DateUtil.formatDate());
        Users updated_user = usersRepository.save(user);
        return modelMapper.map(updated_user, UsersDto.class);
    }

    @Override
    @Transactional
    public UsersDto makeAdmin(int id) {
        Users fetchedUser = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not Exist"));
        fetchedUser.setUpdated_at(DateUtil.formatDate());
        fetchedUser.setStatus(ACTIVE);

        Set<Roles> roles = new HashSet<>();
        Roles fetchedRole = rolesRepository.findByName(ADMIN);
        roles.add(fetchedRole);
        fetchedUser.setRoles(roles);

        Users updatedUser = usersRepository.save(fetchedUser);
        UsersDto dto = modelMapper.map(updatedUser, UsersDto.class);
        dto.setRoleNames(fetchedUser.getRoles());
        return dto;
    }

    @Override
    public UsersDto saveUser(UsersDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(ACTIVE);
        user.setCreated_at(DateUtil.formatDate());

        Set<Roles> roles = new HashSet<>();
        Set<String> rolesGiven = userDto.getRoless();

        for (String role : rolesGiven) {
            Roles fetchedRole = rolesRepository.findByName(role);
            if (fetchedRole == null) {
                fetchedRole = new Roles();
                fetchedRole.setName(role);
                rolesRepository.save(fetchedRole);
            }
            roles.add(fetchedRole);
        }
        user.setRoles(roles);

        Users savedUser = usersRepository.save(user);
        return modelMapper.map(savedUser, UsersDto.class);
    }

}
