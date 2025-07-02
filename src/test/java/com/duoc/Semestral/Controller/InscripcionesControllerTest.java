package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Inscripciones;
import com.duoc.Semestral.Service.InscripcionesService;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class InscripcionesControllerTest {

    @Mock
    private InscripcionesService inscripcionesService;

    @InjectMocks
    private InscripcionesController inscripcionesController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Inscripciones testInscripcion;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(inscripcionesController).build();
        objectMapper = new ObjectMapper();

        testInscripcion = new Inscripciones();
        testInscripcion.setIdInscripcion(1);
        testInscripcion.setCursoInscrito("Programación Java");
        testInscripcion.setFechaInscrito(new Date());
    }

    @Test
    void getAllInscripciones() throws Exception {
        List<Inscripciones> inscripciones = Arrays.asList(testInscripcion);
        when(inscripcionesService.getAllInscripciones()).thenReturn(inscripciones);

        mockMvc.perform(get("/inscripciones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idInscripcion").value(1))
                .andExpect(jsonPath("$[0].cursoInscrito").value("Programación Java"));
    }

    @Test
    void getInscripcionById() throws Exception {
        when(inscripcionesService.getInscripcionById(1)).thenReturn(testInscripcion);

        mockMvc.perform(get("/inscripciones/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idInscripcion").value(1))
                .andExpect(jsonPath("$.cursoInscrito").value("Programación Java"));
    }

    @Test
    void createInscripcion() throws Exception {
        String expectedResponse = "Inscripción creada exitosamente";
        when(inscripcionesService.addInscripcion(any(Inscripciones.class))).thenReturn(expectedResponse);
        mockMvc.perform(post("/inscripciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testInscripcion)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deleteInscripcion() throws Exception {
        String expectedResponse = "Inscripción eliminada";
        when(inscripcionesService.deleteInscripcion(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/inscripciones/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
