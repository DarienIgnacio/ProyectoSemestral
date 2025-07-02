package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Alumno;
import com.duoc.Semestral.Repository.AlumnoRepository;
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
class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    private Alumno testAlumno;

    @BeforeEach
    void setUp() {
        testAlumno = new Alumno();
        testAlumno.setId(1);
        testAlumno.setNombre("Juan Pérez");
        testAlumno.setCorreo("juan.perez@duoc.cl");
    }

    @Test
    void getAllAlumnos() {
        // Given
        List<Alumno> expectedAlumnos = Arrays.asList(testAlumno);
        when(alumnoRepository.findAll()).thenReturn(expectedAlumnos);

        // When
        List<Alumno> result = alumnoService.getAlumnos();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getNombre());
        verify(alumnoRepository, times(1)).findAll();
    }

    @Test
    void getAlumnoById() {
        // Given
        when(alumnoRepository.findById(1)).thenReturn(java.util.Optional.of(testAlumno));

        // When
        Alumno result = alumnoService.getAlumno(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Juan Pérez", result.getNombre());
        assertEquals("juan.perez@duoc.cl", result.getCorreo());
        verify(alumnoRepository, times(1)).findById(1);
    }

    @Test
    void addAlumno() {
        // Given
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(testAlumno);

        // When
        String result = alumnoService.addAlumno(testAlumno);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("Juan Pérez") || result.contains("Alumno"));
        verify(alumnoRepository, times(1)).save(testAlumno);
    }

    @Test
    void testDeleteAlumno() {
        // Given
        doNothing().when(alumnoRepository).deleteById(1);

        // When
        String result = alumnoService.deleteAlumno(1);

        // Then
        assertNotNull(result);
        assertEquals("Alumno eliminado", result);
        verify(alumnoRepository, times(1)).deleteById(1);
    }
}
