package com.example.Webmotors.models.model.version;

import com.example.Webmotors.models.model.Model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Version")
public class Version {
    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    public Model model;


}
