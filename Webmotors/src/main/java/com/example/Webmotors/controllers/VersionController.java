package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.VersionRequestDTO;
import com.example.Webmotors.dto.VersionResponseDTO;
import com.example.Webmotors.services.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/versions")
public class VersionController {

    @Autowired
    private VersionService versionService;

public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @PostMapping("/create")
    public void saveVersion(VersionRequestDTO data) {
        versionService.saveVersion(data);
        return;
    }

    @GetMapping("")
    public List<VersionResponseDTO> getAll() {
        return versionService.getAll();
    }

}
