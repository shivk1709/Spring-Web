package com.project.java.serviceImpl;

import com.project.java.Utils.DateUtils;
import com.project.java.dao.RolesRepository;
import com.project.java.dto.RolesDto;
import com.project.java.entity.Roles;
import com.project.java.service.RolesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RolesDto saveRole(RolesDto rolesDto) {
        Roles role = modelMapper.map(rolesDto, Roles.class);
        role.setCreated_at(DateUtils.formatDate());
        Roles updatedRole = rolesRepository.save(role);
        return modelMapper.map(updatedRole, RolesDto.class);
    }
}
