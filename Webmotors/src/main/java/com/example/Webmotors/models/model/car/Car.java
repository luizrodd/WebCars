package com.example.Webmotors.models.model.car;

import com.example.Webmotors.dto.CarRequestDTO;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.version.Version;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id")
    private Version version;

    @Column(name = "description")
    private String description;

    public Car(int id, String brandId, String modelId, String versionId) {
    }

    public Car(CarRequestDTO data) {
        this.brand = data.brand();
        this.model = data.model();
        this.version = data.version();
        this.description = data.description();

    }

    public Brand getBrand() {
        return this.brand;
    }

    public Model getModel() {
        return this.model;
    }

    public Version getVersion() {
        return this.version;
    }
}