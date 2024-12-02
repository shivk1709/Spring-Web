package com.project.java.serviceImpl;

import com.project.java.entity.Users;
import com.project.java.dao.UsersRepository;
import com.project.java.dto.UsersDto;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String INACTIVE = "INACTIVE";

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper) {
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

        if (user.getStatus().equals(INACTIVE)) {
            return modelMapper.map(user, UsersDto.class);
        }

        user.setStatus(INACTIVE);
        Users updated_user = usersRepository.save(user);
        return modelMapper.map(updated_user, UsersDto.class);
    }

}
