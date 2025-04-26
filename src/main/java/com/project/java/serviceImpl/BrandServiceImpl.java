package com.project.java.serviceImpl;

import com.project.java.dao.BrandsRepository;
import com.project.java.dto.BrandsDto;
import com.project.java.entity.Brands;
import com.project.java.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService
{
    private final ModelMapper modelMapper;
    private final BrandsRepository brandsRepository;

    @Override
    public BrandsDto addBrand(BrandsDto brandsDto)
    {
        Brands brands = brandsRepository.save(modelMapper.map(brandsDto, Brands.class));
        return modelMapper.map(brands, BrandsDto.class);
    }
}
