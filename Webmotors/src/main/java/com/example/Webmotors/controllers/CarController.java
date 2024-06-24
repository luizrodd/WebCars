package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.BrandResponseDTO;
import com.example.Webmotors.dto.CarRequestDTO;
import com.example.Webmotors.dto.CarResponseDTO;
import com.example.Webmotors.models.model.car.Car;
import com.example.Webmotors.repository.BrandRepository;
import com.example.Webmotors.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {


    @Autowired
    private CarRepository carRepository;

    @GetMapping("")
    public List<CarResponseDTO> getAll(){
        List<CarResponseDTO> cars =
                carRepository.findAll().stream().map(CarResponseDTO::new).toList();
        return cars;
    }

    @PostMapping("/create")
    public void saveCar(@RequestBody CarRequestDTO data){
        Car carData = new Car(data);
        carRepository.save(carData);
        return;
    }

}
