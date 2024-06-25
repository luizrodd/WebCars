package com.example.Webmotors.models.model.version;

import com.example.Webmotors.dto.VersionRequestDTO;
import com.example.Webmotors.models.model.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Version")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Version {
    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    public Model model;

    public Version(VersionRequestDTO data, Model model) {
        this.name = data.name();
        this.model = model;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }
}
