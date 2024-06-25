package com.example.Webmotors.dto;

import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.car.Car;


public record CarResponseDTO(int id, String description, String image, BrandSimpleDTO brand) {
    public CarResponseDTO(Car car) {
        this(car.getId(), car.description(), car.image(), new BrandSimpleDTO(car.getBrand(), car));
    }

    public record ModelSimpleDTO(int id, String name, VersionResponseDTO version) {
        public ModelSimpleDTO(Model model, Car car) {
            this(model.getId(), model.getName(), new VersionResponseDTO(car.getVersion()));
        }
    }

    public record BrandSimpleDTO(int id, String name, ModelSimpleDTO model) {
        public BrandSimpleDTO(Brand brand, Car car) {
            this(brand.getId(), brand.getName(), new ModelSimpleDTO(car.getModel(), car));
        }
    }
}