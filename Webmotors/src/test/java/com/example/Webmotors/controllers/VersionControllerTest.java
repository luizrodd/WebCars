package com.example.Webmotors.controllers;

import com.example.Webmotors.dto.VersionRequestDTO;
import com.example.Webmotors.dto.VersionResponseDTO;
import com.example.Webmotors.services.VersionService;
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
public class VersionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VersionService versionService;

    private VersionRequestDTO versionRequestDTO;

    @BeforeEach
    public void setup() {
        versionRequestDTO = new VersionRequestDTO("Version Name", 1);
    }

    @Test
    public void testSaveVersion() throws Exception {
        doNothing().when(versionService).saveVersion(versionRequestDTO);

        mockMvc.perform(post("/versions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Version Name\"}"))
                .andExpect(status().isOk());

        verify(versionService, times(1)).saveVersion(any(VersionRequestDTO.class));
    }

    @Test
    public void testGetAllVersions() throws Exception {
        List<VersionResponseDTO> versions = Arrays.asList(
                new VersionResponseDTO(1,"Version 1", 1),
                new VersionResponseDTO(1,"Version 2", 2)
        );

        when(versionService.getAll()).thenReturn(versions);

        mockMvc.perform(get("/versions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(versionService, times(1)).getAll();
    }
}