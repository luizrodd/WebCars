package com.example.Webmotors.services;

import com.example.Webmotors.dto.VersionRequestDTO;
import com.example.Webmotors.dto.VersionResponseDTO;
import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.version.Version;
import com.example.Webmotors.repository.ModelRepository;
import com.example.Webmotors.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ModelRepository modelRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void saveVersion(VersionRequestDTO data) {
        Model model = modelRepository.findById(data.model_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Version versionData = new Version(data, model);
        versionRepository.save(versionData);
    }

    public List<VersionResponseDTO> getAll() {
        List<VersionResponseDTO> versions = versionRepository.findAll().stream().map(VersionResponseDTO::new).toList();
        return versions;
    }
}
