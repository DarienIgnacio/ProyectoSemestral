package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Curso;
import com.duoc.Semestral.Service.CursoService;
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
class CursoControllerTest {

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Curso testCurso;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();
        objectMapper = new ObjectMapper();

        testCurso = new Curso();
        testCurso.setId(1);
        testCurso.setNombre("Programación Java");
    }

    @Test
    void getAllCursos() throws Exception {
        List<Curso> cursos = Arrays.asList(testCurso);
        when(cursoService.getCursos()).thenReturn(cursos);
        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getCursoById() throws Exception {
        when(cursoService.getCursoById(1)).thenReturn(testCurso);

        mockMvc.perform(get("/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addCurso() throws Exception {
        when(cursoService.saveCurso(any(Curso.class))).thenReturn(testCurso);

        mockMvc.perform(post("/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCurso)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Programación Java"));
    }

    @Test
    void deleteCurso() throws Exception {
        String expectedResponse = "Curso Eliminado";
        when(cursoService.deleteCurso(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
