package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.ModelRequestDTO;
import com.example.Webmotors.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/create")
    public void saveModel(ModelRequestDTO data) {
        modelService.save(data);
        return;
    }
}
