package com.project.java.serviceImpl;

import com.project.java.Util.DateUtil;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private static final String INACTIVE = "INACTIVE";
    private static final String ACTIVE = "ACTIVE";
    private static final String USER = "USER";

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UsersDto.class)).toList();
    }

    @Override
    public UsersDto getUserById(int id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found for Id - " + id));
        return modelMapper.map(user, UsersDto.class);
    }

    @Override
    public List<UsersDto> getUserByEmail(String role) {
        List<Users> users = usersRepository.findByRole(role);
        return users.stream().map(user -> modelMapper.map(user, UsersDto.class)).toList();
    }

    @Override
    public List<UsersDto> getUserByStatus(String status) {
        List<Users> users = usersRepository.findByStatus(status);
        return users.stream().map(user -> modelMapper.map(user, UsersDto.class)).toList();
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
    public UsersDto updateUser(int id, UsersDto userDto) {
        Users fetchedUser = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not Exist"));
        fetchedUser.setEmail(userDto.getEmail());
        fetchedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        fetchedUser.setRole(userDto.getRole().toUpperCase());
        fetchedUser.setDeleted_at(null);
        fetchedUser.setUpdated_at(DateUtil.formatDate());
        fetchedUser.setStatus(ACTIVE);

        Users updatedUser = usersRepository.save(fetchedUser);
        return modelMapper.map(updatedUser, UsersDto.class);
    }

    @Override
    public UsersDto saveUser(UsersDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole().toUpperCase());
        user.setStatus(ACTIVE);
        user.setCreated_at(DateUtil.formatDate());
        user.setUpdated_at(null);
        user.setDeleted_at(null);
        Users savedUser = usersRepository.save(user);
        return modelMapper.map(savedUser, UsersDto.class);
    }

}
