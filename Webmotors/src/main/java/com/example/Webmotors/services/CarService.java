package com.example.Webmotors.services;

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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private VersionRepository versionRepository;

    public CarService(CarRepository carRepository, BrandRepository brandRepository, ModelRepository modelRepository, VersionRepository versionRepository) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.versionRepository = versionRepository;
    }

    public List<CarResponseDTO> getAll(){
        List<CarResponseDTO> cars =
                carRepository.findAll().stream().map(CarResponseDTO::new).toList();
        return cars;
    }

    public void saveCar(@RequestBody CarRequestDTO data) {
        Brand brand = brandRepository.findById(data.brand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Model model = modelRepository.findById(data.model_id())
                .orElseThrow(() -> new RuntimeException("Model not found"));
        Version version = versionRepository.findById(data.version_id())
                .orElseThrow(() -> new RuntimeException("Version not found"));

        Car carData = new Car(data, brand, model, version);
        carRepository.save(carData);
    }

    public void updateCar(int id, CarRequestDTO data) {
        Brand brand = brandRepository.findById(data.brand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Model model = modelRepository.findById(data.model_id())
                .orElseThrow(() -> new RuntimeException("Model not found"));
        Version version = versionRepository.findById(data.version_id())
                .orElseThrow(() -> new RuntimeException("Version not found"));

        Car carData = new Car(data, brand, model, version);
        carRepository.save(carData);
    }

    public void deleteCar(int id) {
        carRepository.deleteById((long) id);
    }
}
