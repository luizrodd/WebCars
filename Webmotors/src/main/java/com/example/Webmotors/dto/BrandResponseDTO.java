package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.brand.Brand;

public record BrandResponseDTO(Integer id, String name) {
    public BrandResponseDTO(Brand brand) {
            this(brand.getId(), brand.getName());
        }
}
