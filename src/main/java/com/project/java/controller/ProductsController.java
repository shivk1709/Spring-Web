package com.project.java.controller;

import com.project.java.dto.ProductsDto;
import com.project.java.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsByName(@RequestParam("name") String name) {
        List<ProductsDto> products = productsService.getProductByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return ResponseEntity.ok("Product Not Found");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductsDto productsDto)
    {
        ProductsDto savedProduct = productsService.save(productsDto);
        if(savedProduct != null)
        {
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        }
        return ResponseEntity.ok("Error Found while saving");
    }

    @GetMapping()
    public ResponseEntity<?> getAllProducts() {
        List<ProductsDto> products = productsService.getAllProducts();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return ResponseEntity.ok("Product Not Found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {   productsService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductsDto productsDto)
    {
        ProductsDto updated = productsService.update(productsDto, id);
        if(updated != null)
        {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return ResponseEntity.ok("Error Found while saving");
    }


}
