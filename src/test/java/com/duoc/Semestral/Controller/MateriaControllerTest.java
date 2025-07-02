package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Materia;
import com.duoc.Semestral.Service.MateriaService;
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
class MateriaControllerTest {

    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Materia testMateria;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(materiaController).build();
        objectMapper = new ObjectMapper();

        testMateria = new Materia();
        testMateria.setIdMateria(1);
        testMateria.setNombreMateria("Algoritmos y Estructuras de Datos");
        testMateria.setDescripcionMateria("Fundamentos de algoritmos");
    }

    @Test
    void getAllMaterias() throws Exception {
        List<Materia> materias = Arrays.asList(testMateria);
        when(materiaService.getMaterias()).thenReturn(materias);

        mockMvc.perform(get("/materias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idMateria").value(1))
                .andExpect(jsonPath("$[0].nombreMateria").value("Algoritmos y Estructuras de Datos"));
    }

    @Test
    void getMateriaById() throws Exception {
        when(materiaService.getMateria(1)).thenReturn(testMateria);

        mockMvc.perform(get("/materias/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idMateria").value(1))
                .andExpect(jsonPath("$.nombreMateria").value("Algoritmos y Estructuras de Datos"));
    }

    @Test
    void createMateria() throws Exception {
        String expectedResponse = "Materia creada exitosamente";
        when(materiaService.addMateria(any(Materia.class))).thenReturn(expectedResponse);
        mockMvc.perform(post("/materias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMateria)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deleteMateria() throws Exception {
        String expectedResponse = "Materia eliminada";
        when(materiaService.deleteMateria(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/materias/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
