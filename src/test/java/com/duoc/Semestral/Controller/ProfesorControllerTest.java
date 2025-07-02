package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Profesor;
import com.duoc.Semestral.Service.ProfesorService;
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
class ProfesorControllerTest {

    @Mock
    private ProfesorService profesorService;

    @InjectMocks
    private ProfesorController profesorController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Profesor testProfesor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(profesorController).build();
        objectMapper = new ObjectMapper();

        testProfesor = new Profesor();
        testProfesor.setId(1);
        testProfesor.setNombre("Dr. María González");
        testProfesor.setCorreo("maria.gonzalez@duoc.cl");
        testProfesor.setDepartamento("Informática");
    }

    @Test
    void getAllProfesores() throws Exception {
        List<Profesor> profesores = Arrays.asList(testProfesor);
        when(profesorService.getProfesores()).thenReturn(profesores);

        mockMvc.perform(get("/profesores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Dr. María González"));
    }

    @Test
    void getProfesorById() throws Exception {
        when(profesorService.getProfesor(1)).thenReturn(testProfesor);

        mockMvc.perform(get("/profesores/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Dr. María González"));
    }

    @Test
    void createProfesor() throws Exception {
        String expectedResponse = "Profesor creado exitosamente";
        when(profesorService.addProfesor(any(Profesor.class))).thenReturn(expectedResponse);
        mockMvc.perform(post("/profesores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testProfesor)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deleteProfesor() throws Exception {
        String expectedResponse = "Profesor eliminado";
        when(profesorService.deleteProfesor(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/profesores/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
