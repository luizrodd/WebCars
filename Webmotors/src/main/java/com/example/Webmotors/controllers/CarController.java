package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.CarRequestDTO;
import com.example.Webmotors.dto.CarResponseDTO;
import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.brand.Brand;
import com.example.Webmotors.models.model.car.Car;
import com.example.Webmotors.models.model.version.Version;
import com.example.Webmotors.repository.BrandRepository;
import com.example.Webmotors.repository.CarRepository;
import com.example.Webmotors.repository.ModelRepository;
import com.example.Webmotors.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {


    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private VersionRepository versionRepository;

    @GetMapping("")
    public List<CarResponseDTO> getAll(){
        List<CarResponseDTO> cars =
                carRepository.findAll().stream().map(CarResponseDTO::new).toList();
        return cars;
    }

    @PostMapping("/create")
    public void saveCar(@RequestBody CarRequestDTO data){
        Brand brand = brandRepository.findById(data.brand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Model model = modelRepository.findById(data.model_id())
                .orElseThrow(() -> new RuntimeException("Model not found"));
        Version version = versionRepository.findById(data.version_id())
                .orElseThrow(() -> new RuntimeException("Version not found"));

        Car carData = new Car(data, brand, model, version);
        carRepository.save(carData);
    }


}
