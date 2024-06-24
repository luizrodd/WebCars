package com.example.Webmotors.repository;

import com.example.Webmotors.models.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
