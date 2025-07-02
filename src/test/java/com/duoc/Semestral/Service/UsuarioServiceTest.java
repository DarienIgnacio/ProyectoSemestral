package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Usuario;
import com.duoc.Semestral.Repository.UsuarioRepository;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario testUsuario;

    @BeforeEach
    void setUp() {
        testUsuario = new Usuario();
        testUsuario.setId(1);
        testUsuario.setNombre("Carlos López");
        testUsuario.setApellidos("Martínez");
        testUsuario.setRut("12345678-9");
        testUsuario.setCorreo("carlos.lopez@duoc.cl");
    }

    @Test
    void getAllUsuarios() {
        List<Usuario> expectedUsuarios = Arrays.asList(testUsuario);
        when(usuarioRepository.findAll()).thenReturn(expectedUsuarios);

        List<Usuario> result = usuarioService.getAllUsuarios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Carlos López", result.get(0).getNombre());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void getUsuarioById() {
        when(usuarioRepository.findById(1)).thenReturn(java.util.Optional.of(testUsuario));

        Usuario result = usuarioService.getAllUsuario(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Carlos López", result.getNombre());
        assertEquals("12345678-9", result.getRut());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void addUsuario() {
        // Given
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(testUsuario);

        // When
        String result = usuarioService.addUsuario(testUsuario);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("Carlos López") || result.contains("Usuario"));
        verify(usuarioRepository, times(1)).save(testUsuario);
    }

    @Test
    void testDeleteUsuario() {
        // Given
        doNothing().when(usuarioRepository).deleteById(1);

        // When
        String result = usuarioService.deleteUsuario(1);

        // Then
        assertNotNull(result);
        assertEquals("Usuario eliminado", result);
        verify(usuarioRepository, times(1)).deleteById(1);
    }
}
