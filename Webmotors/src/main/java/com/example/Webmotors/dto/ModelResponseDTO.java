package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.Model;

import java.util.List;

public record ModelResponseDTO(Integer id, String name, Integer brand_id, List<VersionResponseDTO> versions) {
    public ModelResponseDTO(Model model) {
        this(model.getId(), model.getName(), model.brand.getId(), model.getVersions().stream()
                .map(VersionResponseDTO::new)
                .toList());
    }
}
