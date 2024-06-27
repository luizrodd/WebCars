package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.ModelRequestDTO;
import com.example.Webmotors.dto.ModelResponseDTO;
import com.example.Webmotors.dto.VersionResponseDTO;
import com.example.Webmotors.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelService modelService;

    private ModelRequestDTO modelRequestDTO;

    @BeforeEach
    public void setup() {
        modelRequestDTO = new ModelRequestDTO("Model Name", 1);
    }

    @Test
    public void testSaveModel() throws Exception {
        doNothing().when(modelService).save(modelRequestDTO);

        mockMvc.perform(post("/models/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Model Name\"}"))
                .andExpect(status().isOk());

        verify(modelService, times(1)).save(any(ModelRequestDTO.class));
    }

    @Test
    public void testGetAllModels() throws Exception {

        List<VersionResponseDTO> versionList1 = Arrays.asList(
                new VersionResponseDTO(1, "Version 1.1", 1),
                new VersionResponseDTO(2, "Version 1.2", 1)
        );

        List<VersionResponseDTO> versionList2 = Arrays.asList(
                new VersionResponseDTO(3, "Version 2.1", 2),
                new VersionResponseDTO(4, "Version 2.2", 2)
        );

        List<ModelResponseDTO> models = Arrays.asList(
                new ModelResponseDTO(1, "Model 1", 1, versionList1),
                new ModelResponseDTO(2, "Model 2", 1, versionList2)
        );

        when(modelService.getAll()).thenReturn(models);

        mockMvc.perform(get("/models"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(modelService, times(1)).getAll();
    }

}