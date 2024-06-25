package com.example.Webmotors.services;

import com.example.Webmotors.dto.ModelRequestDTO;
import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void save(ModelRequestDTO model) {
        Model models = new Model(model);
        modelRepository.save(models);
        return;
    }
}
