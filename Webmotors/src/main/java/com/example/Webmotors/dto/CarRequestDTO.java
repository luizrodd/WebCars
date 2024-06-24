package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.version.Version;

public record CarRequestDTO(String description, Brand brand, Model model, Version version) {
}
