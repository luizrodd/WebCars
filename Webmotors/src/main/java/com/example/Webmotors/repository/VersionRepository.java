package com.example.Webmotors.repository;

import com.example.Webmotors.models.model.car.Car;
import com.example.Webmotors.models.model.version.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository  extends JpaRepository<Version, Integer> {
}
