package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.CarRequestDTO;
import com.example.Webmotors.dto.CarResponseDTO;
import com.example.Webmotors.services.CarService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarResponseDTO.class)) }),
            @ApiResponse(responseCode = "204", content = @Content)
    })
    public ResponseEntity<List<CarResponseDTO>> getAll(){
        var cars = carService.getAll();
        if(cars.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", content = @Content)
    @ApiResponse(responseCode = "400", content = @Content)
    public ResponseEntity<Void> saveCar(@RequestBody CarRequestDTO data){
        if(data.brand_id() == null || data.model_id() == null || data.version_id() == null || data.image() == null || data.description() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        carService.saveCar(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", content = @Content)
    @ApiResponse(responseCode = "400", content = @Content)
    public ResponseEntity<Void> updateCar(@PathVariable int id, @RequestBody CarRequestDTO data){
        if(data.brand_id() == null || data.model_id() == null || data.version_id() == null || data.image() == null || data.description() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        carService.updateCar(id, data);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
        return;
    }


}
