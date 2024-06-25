package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;

import java.util.List;
import java.util.stream.Collectors;

public record BrandResponseDTO(Integer id, String name, List<ModelResponseDTO> models) {
    public BrandResponseDTO(Brand brand) {
        this(brand.getId(), brand.getName(), brand.getModels().stream()
                .map(ModelResponseDTO::new)
                .collect(Collectors.toList()));
    }

}