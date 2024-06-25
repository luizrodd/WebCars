package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.BrandRequestDTO;
import com.example.Webmotors.dto.BrandResponseDTO;
import com.example.Webmotors.services.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("")
    public List<BrandResponseDTO> getAll() {
        var brands = brandService.getAll();
        return brands;
    }

    @PostMapping("/create")
    public void saveBrand(@RequestBody BrandRequestDTO data) {
        brandService.saveBrand(data);
        return;
    }

    @GetMapping("/{id}")
    public BrandResponseDTO getBrand(@PathVariable Integer id) {
        var brand = brandService.getBrand(id);
        return brand;
    }

    @PutMapping("/{id}")
    public void updateBrand(@PathVariable Integer id, @RequestBody BrandRequestDTO data) {
        brandService.updateBrand(id, data);
        return;
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return;
    }

}
