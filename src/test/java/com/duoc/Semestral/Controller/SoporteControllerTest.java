package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Soporte;
import com.duoc.Semestral.Service.SoporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SoporteControllerTest {

    @Mock
    private SoporteService soporteService;

    @InjectMocks
    private SoporteController soporteController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Soporte testSoporte;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(soporteController).build();
        objectMapper = new ObjectMapper();

        testSoporte = new Soporte();
        testSoporte.setId(1);
        testSoporte.setNombre("Ana García");
        testSoporte.setDepartamento("IT Support");
        testSoporte.setCorreo("ana.garcia@duoc.cl");
    }

    @Test
    void getAllSoportes() throws Exception {
        List<Soporte> soportes = Arrays.asList(testSoporte);
        when(soporteService.getSoportes()).thenReturn(soportes);

        mockMvc.perform(get("/soportes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Ana García"));
    }

    @Test
    void getSoporteById() throws Exception {
        when(soporteService.getSoporteById(1)).thenReturn(testSoporte);

        mockMvc.perform(get("/soportes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.departamento").value("IT Support"));
    }

    @Test
    void createSoporte() throws Exception {
        String expectedResponse = "Soporte creado exitosamente";
        when(soporteService.addSoporte(any(Soporte.class))).thenReturn(expectedResponse);
        mockMvc.perform(post("/soportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testSoporte)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deleteSoporte() throws Exception {
        String expectedResponse = "Soporte eliminado";
        when(soporteService.deleteSoporte(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/soportes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
