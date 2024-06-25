package com.example.Webmotors.services;

import com.example.Webmotors.dto.BrandRequestDTO;
import com.example.Webmotors.dto.BrandResponseDTO;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    public List<BrandResponseDTO> getAll(){
        List<BrandResponseDTO> brands =
                brandRepository.findAll().stream().map(BrandResponseDTO::new).toList();
        return brands;
    }

    public void saveBrand(BrandRequestDTO data){
        Brand brandData = new Brand(data);
        brandRepository.save(brandData);
        return;
    }

    public BrandResponseDTO getBrand(Integer id){
        Brand brand = brandRepository.findById(id).orElseThrow();
        return new BrandResponseDTO(brand);
    }

    public void updateBrand(Integer id, BrandRequestDTO data){
        Brand brand = brandRepository.findById(id).orElseThrow();
        brand.update(data);
        brandRepository.save(brand);
        return;
    }

    public void deleteBrand(Integer id){
        Brand brand = brandRepository.findById(id).orElseThrow();
        brandRepository.delete(brand);
        return;
    }
}
