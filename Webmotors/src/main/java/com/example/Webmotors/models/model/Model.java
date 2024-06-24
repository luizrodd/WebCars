package com.example.Webmotors.models.model;

import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.version.Version;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Model")
public class Model {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    public Brand brand;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Version> versions;
}
