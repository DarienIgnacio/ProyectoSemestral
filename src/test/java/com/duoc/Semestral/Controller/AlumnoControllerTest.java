package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Alumno;
import com.duoc.Semestral.Service.AlumnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Alumno testAlumno;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();
        objectMapper = new ObjectMapper();

        testAlumno = new Alumno();
        testAlumno.setId(1);
        testAlumno.setNombre("Juan Pérez");
        testAlumno.setCorreo("juan.perez@duoc.cl");
    }

    @Test
    void getAllAlumnos() throws Exception {
        List<Alumno> alumnos = Arrays.asList(testAlumno);
        when(alumnoService.getAlumnos()).thenReturn(alumnos);

        mockMvc.perform(get("/alumnos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAlumnoById() throws Exception {
        when(alumnoService.getAlumno(1)).thenReturn(testAlumno);

        mockMvc.perform(get("/alumnos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test

    void createAlumno() throws Exception {
        String expectedResponse = "Alumno creado exitosamente";
        when(alumnoService.addAlumno(any(Alumno.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAlumno)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deleteAlumno() throws Exception {
        String expectedResponse = "Alumno eliminado";
        when(alumnoService.deleteAlumno(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/alumnos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
