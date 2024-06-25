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
import com.example.Webmotors.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {


    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public List<CarResponseDTO> getAll(){
        var cars = carService.getAll();
        return cars;
    }

    @PostMapping("/create")
    public void saveCar(@RequestBody CarRequestDTO data){
        carService.saveCar(data);
        return;
    }

    @PutMapping("/update/{id}")
    public void updateCar(@PathVariable int id, @RequestBody CarRequestDTO data){
        carService.updateCar(id, data);
        return;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
        return;
    }


}
