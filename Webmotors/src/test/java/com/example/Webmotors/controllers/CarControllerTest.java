package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.CarResponseDTO;
import com.example.Webmotors.dto.VersionResponseDTO;
import com.example.Webmotors.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllCars() throws Exception {
        CarResponseDTO.ModelSimpleDTO models = new CarResponseDTO.ModelSimpleDTO(1, "Model 1", new VersionResponseDTO(1, "Version 1", 1));

        CarResponseDTO.BrandSimpleDTO brands  = new CarResponseDTO.BrandSimpleDTO(1, "Brand 1", models);

        List<CarResponseDTO> cars = Arrays.asList(
                new CarResponseDTO(1, "Car 1", "Brand 1", brands),
                new CarResponseDTO(2, "Car 2", "Brand 2", brands)
        );

        when(carService.getAll()).thenReturn(cars);

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(carService, times(1)).getAll();
    }

    @Test
    public void testSaveCar() throws Exception {
        doNothing().when(carService).saveCar(any());

        mockMvc.perform(post("/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Car 1\"}"))
                .andExpect(status().isOk());

        verify(carService, times(1)).saveCar(any());
    }

    @Test
    public void testUpdateCar() throws Exception {
        doNothing().when(carService).updateCar(anyInt(), any());

        mockMvc.perform(put("/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Car 1\"}"))
                .andExpect(status().isOk());

        verify(carService, times(1)).updateCar(anyInt(), any());
    }

    @Test
    public void testDeleteCar() throws Exception {
        doNothing().when(carService).deleteCar(anyInt());

        mockMvc.perform(delete("/cars/1"))
                .andExpect(status().isOk());

        verify(carService, times(1)).deleteCar(anyInt());
    }
}
