package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Curso;
import com.duoc.Semestral.Repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso testCurso;

    @BeforeEach
    void setUp() {
        testCurso = new Curso();
        testCurso.setId(1);
        testCurso.setNombre("Programación Java");
    }

    @Test
    void getAllCursos() {
        List<Curso> expectedCursos = Arrays.asList(testCurso);
        when(cursoRepository.findAll()).thenReturn(expectedCursos);

        List<Curso> result = cursoService.getCursos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Programación Java", result.get(0).getNombre());
        verify(cursoRepository, times(1)).findAll();
    }

    @Test
    void getCursoById() {
        when(cursoRepository.findById(1)).thenReturn(java.util.Optional.of(testCurso));

        Curso result = cursoService.getCursoById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Programación Java", result.getNombre());
        verify(cursoRepository, times(1)).findById(1);
    }
}
