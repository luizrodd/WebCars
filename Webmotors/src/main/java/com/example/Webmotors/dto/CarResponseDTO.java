package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.version.Version;
import com.example.Webmotors.models.model.car.Car;

public record CarResponseDTO(Brand brand, Model model, Version version) {
    public CarResponseDTO(Car car) {
        this(car.getBrand(), car.getModel(), car.getVersion());
    }
}
