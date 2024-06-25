package com.example.Webmotors.models.model.car;

import com.example.Webmotors.dto.CarRequestDTO;
import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.version.Version;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "Car")
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private String image;

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

    public Brand getBrand() {
        return this.brand;
    }

    public Model getModel() {
        return this.model;
    }

    public Version getVersion() {
        return this.version;
    }

    public Car() {
    }

    public Car(CarRequestDTO data, Brand brand, Model model, Version version) {
        this.image = data.image();
        this.description = data.description();
        this.brand = brand;
        this.model = model;
        this.version = version;
    }

    public String description() {
        return this.description;
    }


    public int getId() {
        return this.id;
    }

    public String image() {
        return this.image;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}