package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.car.Car;


public record CarResponseDTO(int id, String description, BrandSimpleDTO brand) {
    public CarResponseDTO(Car car) {
        this(car.getId(), car.description(), new BrandSimpleDTO(car.getBrand(), car.getModel()));
    }

    public record ModelSimpleDTO(int id, String name, VersionResponseDTO version) {
        public ModelSimpleDTO(Model model) {
            this(model.getId(), model.getName(), new VersionResponseDTO(model.getVersion()));
        }
    }

    public record BrandSimpleDTO(int id, String name, ModelSimpleDTO model) {
        public BrandSimpleDTO(Brand brand, Model model) {
            this(brand.getId(), brand.getName(), new ModelSimpleDTO(model));
        }
    }
}