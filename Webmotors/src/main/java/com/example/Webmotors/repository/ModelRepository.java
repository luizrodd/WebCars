package com.example.Webmotors.repository;

import com.example.Webmotors.models.model.Model;
import com.example.Webmotors.models.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
