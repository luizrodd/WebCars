package com.example.Webmotors.models.model;

import com.example.Webmotors.dto.ModelRequestDTO;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.version.Version;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "Model")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Model(ModelRequestDTO data, Brand brand) {
        this.name = data.name();
        this.brand = brand;
    }
    public Model(int id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }


    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public List<Version> getVersions() {
        return this.versions;
    }

    public Version getVersion() {
        return this.versions.get(0);
    }

    public void setId(int i) {
        this.id = i;
    }
}
