package com.example.Webmotors.models.model.brand;

import com.example.Webmotors.dto.BrandRequestDTO;
import com.example.Webmotors.models.model.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "Brand")
@Entity(name = "Brand")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Model> models;

    public String getName() {
        return this.name;
    }
    public Integer getId() {
        return this.id;
    }

    public Brand(BrandRequestDTO data){
        this.name = data.name();
    }


    public void update(BrandRequestDTO data) {
        this.name = data.name();
    }
}
