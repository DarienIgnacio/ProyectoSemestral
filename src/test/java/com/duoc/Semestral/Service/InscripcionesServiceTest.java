package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Inscripciones;
import com.duoc.Semestral.Repository.InscripcionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InscripcionesServiceTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private InscripcionesService inscripcionesService;

    private Inscripciones testInscripcion;

    @BeforeEach
    void setUp() {
        testInscripcion = new Inscripciones();
        testInscripcion.setIdInscripcion(1);
        testInscripcion.setCursoInscrito("Programación Java");
        testInscripcion.setFechaInscrito(new Date());
    }

    @Test
    void getAllInscripciones() {
        List<Inscripciones> expectedInscripciones = Arrays.asList(testInscripcion);
        when(inscripcionRepository.findAll()).thenReturn(expectedInscripciones);

        List<Inscripciones> result = inscripcionesService.getAllInscripciones();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Programación Java", result.get(0).getCursoInscrito());
        verify(inscripcionRepository, times(1)).findAll();
    }

    @Test
    void addInscripcion() {
        when(inscripcionRepository.save(any(Inscripciones.class))).thenReturn(testInscripcion);

        String result = inscripcionesService.addInscripcion(testInscripcion);

        assertNotNull(result);
        assertEquals("Inscripcion realizada!", result);
        verify(inscripcionRepository, times(1)).save(testInscripcion);
    }

    @Test
    void getInscripcionById() {
        // Given
        when(inscripcionRepository.findById(1)).thenReturn(java.util.Optional.of(testInscripcion));

        // When
        Inscripciones result = inscripcionesService.getInscripcionById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getIdInscripcion());
        assertEquals("Programación Java", result.getCursoInscrito());
        verify(inscripcionRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteInscripcion() {
        // Given
        doNothing().when(inscripcionRepository).deleteById(1);

        // When
        String result = inscripcionesService.deleteInscripcion(1);

        // Then
        assertNotNull(result);
        assertEquals("Inscripción eliminada", result);
        verify(inscripcionRepository, times(1)).deleteById(1);
    }
}
