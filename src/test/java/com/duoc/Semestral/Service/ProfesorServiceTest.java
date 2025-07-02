package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Profesor;
import com.duoc.Semestral.Repository.ProfesorRepository;
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
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    private Profesor testProfesor;

    @BeforeEach
    void setUp() {
        testProfesor = new Profesor();
        testProfesor.setId(1);
        testProfesor.setNombre("Dr. María González");
        testProfesor.setCorreo("maria.gonzalez@duoc.cl");
        testProfesor.setDepartamento("Informática");
    }

    @Test
    void getAllProfesores() {
        List<Profesor> expectedProfesores = Arrays.asList(testProfesor);
        when(profesorRepository.findAll()).thenReturn(expectedProfesores);

        List<Profesor> result = profesorService.getProfesores();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Dr. María González", result.get(0).getNombre());
        verify(profesorRepository, times(1)).findAll();
    }

    @Test
    void getProfesorById() {
        when(profesorRepository.findById(1)).thenReturn(java.util.Optional.of(testProfesor));

        Profesor result = profesorService.getProfesor(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Dr. María González", result.getNombre());
        assertEquals("Informática", result.getDepartamento());
        verify(profesorRepository, times(1)).findById(1);
    }

    @Test
    void addProfesor() {
        // Given
        when(profesorRepository.save(any(Profesor.class))).thenReturn(testProfesor);

        // When
        String result = profesorService.addProfesor(testProfesor);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("Dr. María González") || result.contains("Profesor"));
        verify(profesorRepository, times(1)).save(testProfesor);
    }

    @Test
    void testDeleteProfesor() {
        // Given
        doNothing().when(profesorRepository).deleteById(1);

        // When
        String result = profesorService.deleteProfesor(1);

        // Then
        assertNotNull(result);
        assertEquals("Profesor eliminado", result);
        verify(profesorRepository, times(1)).deleteById(1);
    }
}
