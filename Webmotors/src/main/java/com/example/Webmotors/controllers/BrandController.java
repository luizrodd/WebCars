package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.BrandRequestDTO;
import com.example.Webmotors.dto.BrandResponseDTO;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("")
    public List<BrandResponseDTO> getAll(){
        List<BrandResponseDTO> brands =
                brandRepository.findAll().stream().map(BrandResponseDTO::new).toList();
        return brands;
    }

    @PostMapping("/create")
    public void saveBrand(@RequestBody BrandRequestDTO data){
        Brand brandData = new Brand(data);
        brandRepository.save(brandData);
        return;
    }

    @GetMapping("/{id}")
    public BrandResponseDTO getBrand(@PathVariable Long id){
        Brand brand = brandRepository.findById(id).orElseThrow();
        return new BrandResponseDTO(brand);
    }

    @PutMapping("/{id}")
    public void updateBrand(@PathVariable Long id, @RequestBody BrandRequestDTO data){
        Brand brand = brandRepository.findById(id).orElseThrow();
        brand.update(data);
        brandRepository.save(brand);
        return;
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id){
        Brand brand = brandRepository.findById(id).orElseThrow();
        brandRepository.delete(brand);
        return;
    }

}
