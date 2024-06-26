package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.version.Version;

public record VersionResponseDTO(Integer id, String name, Integer model_id) {
    public VersionResponseDTO(Version version) {
        this(version.getId(), version.getName(), version.model != null ? version.model.getId() : null);
    }
}
