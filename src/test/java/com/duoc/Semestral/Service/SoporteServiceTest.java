package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Soporte;
import com.duoc.Semestral.Repository.SoporteRepository;
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
class SoporteServiceTest {

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private SoporteService soporteService;

    private Soporte testSoporte;

    @BeforeEach
    void setUp() {
        testSoporte = new Soporte();
        testSoporte.setId(1);
        testSoporte.setNombre("Ana García");
        testSoporte.setDepartamento("IT Support");
        testSoporte.setCorreo("ana.garcia@duoc.cl");
    }

    @Test
    void getAllSoportes() {
        List<Soporte> expectedSoportes = Arrays.asList(testSoporte);
        when(soporteRepository.findAll()).thenReturn(expectedSoportes);

        List<Soporte> result = soporteService.getSoportes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ana García", result.get(0).getNombre());
        assertEquals("IT Support", result.get(0).getDepartamento());
        verify(soporteRepository, times(1)).findAll();
    }

    @Test
    void addSoporte() {
        when(soporteRepository.save(any(Soporte.class))).thenReturn(testSoporte);

        String result = soporteService.addSoporte(testSoporte);
        assertNotNull(result);
        assertTrue(result.contains("Ana García") || result.contains("Soporte"));
        verify(soporteRepository, times(1)).save(testSoporte);
    }

    @Test
    void getSoporteById() {
        // Given
        when(soporteRepository.findById(1)).thenReturn(java.util.Optional.of(testSoporte));

        // When
        Soporte result = soporteService.getSoporteById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Ana García", result.getNombre());
        assertEquals("IT Support", result.getDepartamento());
        verify(soporteRepository, times(1)).findById(1);
    }

    @Test
    void deleteSoporte() {
        // Given
        doNothing().when(soporteRepository).deleteById(1);

        // When
        String result = soporteService.deleteSoporte(1);

        // Then
        assertNotNull(result);
        assertEquals("Soporte eliminado", result);
        verify(soporteRepository, times(1)).deleteById(1);
    }
}
