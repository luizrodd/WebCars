package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.ModelRequestDTO;
import com.example.Webmotors.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        modelRequestDTO = new ModelRequestDTO("Model Name");
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
}