package com.project.java.serviceImpl;

import com.project.java.Util.DateUtil;
import com.project.java.dao.UsersRepository;
import com.project.java.dto.UsersDto;
import com.project.java.entity.Users;
import com.project.java.exception.ResourceNotFoundException;
import com.project.java.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    private static final String INACTIVE = "INACTIVE";
    private static final String ACTIVE = "ACTIVE";
    private static final String USER = "USER";

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public UsersDto updateUser(int id, UsersDto userDto) {
        Users fetchedUser = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not Exist"));
        fetchedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        fetchedUser.setUpdated_at(DateUtil.formatDate());
        fetchedUser.setDeleted_at(null);
        fetchedUser.setStatus(ACTIVE);

        Users updatedUser = usersRepository.save(fetchedUser);
        return modelMapper.map(updatedUser, UsersDto.class);
    }

    @Override
    public UsersDto saveUser(UsersDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(USER);
        user.setStatus(ACTIVE);
        user.setCreated_at(DateUtil.formatDate());
        user.setUpdated_at(null);
        user.setDeleted_at(null);
        Users savedUser = usersRepository.save(user);
        return modelMapper.map(savedUser, UsersDto.class);
    }

}
