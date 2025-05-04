package com.project.java.serviceImpl;

import com.project.java.dao.BrandsRepository;
import com.project.java.dto.BrandsDto;
import com.project.java.entity.Brands;
import com.project.java.entity.Categories;
import com.project.java.entity.Products;
import com.project.java.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BrandServiceImpl implements BrandService
{
    private final ModelMapper modelMapper;
    private final BrandsRepository brandsRepository;

    @Override
    @Transactional
    public BrandsDto addBrand(BrandsDto brandsDto)
    {
        log.info("Attempting to add/update brand: {}", brandsDto.getName());

        Optional<Brands> brandOpt = brandsRepository.findByName(brandsDto.getName());
        Brands savedBrand;

        if (brandOpt.isPresent())
        {
            Brands existingBrand = brandOpt.get();
            log.debug("Existing brand found: {}", existingBrand.getId());

            Brands newBrandData = modelMapper.map(brandsDto, Brands.class);
            List<Categories> existingCategories = existingBrand.getCategories();

            for (Categories newCategory : newBrandData.getCategories())
            {
                Optional<Categories> matchedCategoryOpt = existingCategories.stream()
                        .filter(c -> c.getName().equalsIgnoreCase(newCategory.getName()))
                        .findFirst();

                if (matchedCategoryOpt.isPresent())
                {
                    Categories existingCategory = matchedCategoryOpt.get();
                    log.debug("Matching category found: {}", existingCategory.getName());

                    List<Products> existingProducts = existingCategory.getProducts();
                    for (Products newProduct : newCategory.getProducts())
                    {
                        boolean productExists = existingProducts.stream()
                                .anyMatch(p -> p.getName().equals(newProduct.getName())
                                        && p.getFlavour().equals(newProduct.getFlavour())
                                        && p.getWeight().equals(newProduct.getWeight()));

                        if (productExists)
                        {
                            log.warn("Duplicate product detected - Name: {}, Flavour: {}, Weight: {} under brand: {} and category: {}",
                                    newProduct.getName(),
                                    newProduct.getFlavour(),
                                    newProduct.getWeight(),
                                    existingBrand.getName(),
                                    existingCategory.getName());
                        } else
                        {
                            log.info("Adding new product: {} to category: {}",
                                    newProduct.getName(),
                                    existingCategory.getName());
                            existingProducts.add(newProduct);
                            newProduct.setCategory(existingCategory);
                        }
                    }
                } else
                {
                    log.info("Adding new category: {} to brand: {}",
                            newCategory.getName(),
                            existingBrand.getName());
                    newCategory.setBrands(existingBrand);
                    existingCategories.add(newCategory);
                }
            }

            savedBrand = brandsRepository.save(existingBrand);
            log.info("Updated existing brand: {}", savedBrand.getId());
        } else
        {
            log.info("Creating new brand: {}", brandsDto.getName());
            Brands newBrand = modelMapper.map(brandsDto, Brands.class);

            for (Categories category : newBrand.getCategories())
            {
                category.setBrands(newBrand);
                log.debug("Setting brand for category: {}", category.getName());

                for (Products product : category.getProducts())
                {
                    product.setCategory(category);
                    log.trace("Setting category for product: {}", product.getName());
                }
            }

            savedBrand = brandsRepository.save(newBrand);
            log.info("Created new brand with ID: {}", savedBrand.getId());
        }

        return modelMapper.map(savedBrand, BrandsDto.class);
    }

    @Override
    public List<BrandsDto> getAllBrands()
    {
        List<Brands> brands = brandsRepository.findAll();
        return brands.stream().map(brand -> modelMapper.map(brand, BrandsDto.class)).toList();
    }
}
