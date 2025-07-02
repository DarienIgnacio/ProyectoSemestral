package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Materia;
import com.duoc.Semestral.Repository.MateriaRepository;
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
class MateriaServiceTest {

    @Mock
    private MateriaRepository materiaRepository;

    @InjectMocks
    private MateriaService materiaService;

    private Materia testMateria;

    @BeforeEach
    void setUp() {
        testMateria = new Materia();
        testMateria.setIdMateria(1);
        testMateria.setNombreMateria("Algoritmos y Estructuras de Datos");
        testMateria.setDescripcionMateria("Fundamentos de algoritmos");
    }

    @Test
    void getAllMaterias() {
        List<Materia> expectedMaterias = Arrays.asList(testMateria);
        when(materiaRepository.findAll()).thenReturn(expectedMaterias);

        List<Materia> result = materiaService.getMaterias();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Algoritmos y Estructuras de Datos", result.get(0).getNombreMateria());
        verify(materiaRepository, times(1)).findAll();
    }

    @Test
    void getMateriaById() {
        when(materiaRepository.findById(1)).thenReturn(java.util.Optional.of(testMateria));

        Materia result = materiaService.getMateria(1);

        assertNotNull(result);
        assertEquals(1, result.getIdMateria());
        assertEquals("Algoritmos y Estructuras de Datos", result.getNombreMateria());
        verify(materiaRepository, times(1)).findById(1);
    }

    @Test
    void addMateria() {
        // Given
        when(materiaRepository.save(any(Materia.class))).thenReturn(testMateria);

        // When
        String result = materiaService.addMateria(testMateria);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("Algoritmos y Estructuras de Datos") || result.contains("Materia"));
        verify(materiaRepository, times(1)).save(testMateria);
    }

    @Test
    void testDeleteMateria() {
        // Given
        doNothing().when(materiaRepository).deleteById(1);

        // When
        String result = materiaService.deleteMateria(1);

        // Then
        assertNotNull(result);
        assertEquals("Materia eliminada", result);
        verify(materiaRepository, times(1)).deleteById(1);
    }
}
