package com.example.Webmotors.services;

import com.example.Webmotors.dto.VersionRequestDTO;
import com.example.Webmotors.models.model.version.Version;
import com.example.Webmotors.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void saveVersion(VersionRequestDTO data) {
        Version versionData = new Version(data);
        versionRepository.save(versionData);
    }
}
