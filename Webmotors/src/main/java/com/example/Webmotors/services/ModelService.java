package com.example.Webmotors.services;

import com.example.Webmotors.dto.ModelRequestDTO;
import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.repository.BrandRepository;
import com.example.Webmotors.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void save(@RequestBody ModelRequestDTO data) {
        Brand brand = brandRepository.findById(data.brand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Model modelData = new Model(data, brand);
        modelRepository.save(modelData);
    }
}
