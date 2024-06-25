package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.CarRequestDTO;
import com.example.Webmotors.dto.CarResponseDTO;
import com.example.Webmotors.services.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cars")
@Tag(name = "Car")
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
