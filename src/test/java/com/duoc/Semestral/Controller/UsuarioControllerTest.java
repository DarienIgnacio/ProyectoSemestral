package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Usuario;
import com.duoc.Semestral.Service.UsuarioService;
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
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Usuario testUsuario;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        objectMapper = new ObjectMapper();

        testUsuario = new Usuario();
        testUsuario.setId(1);
        testUsuario.setNombre("Carlos López");
        testUsuario.setApellidos("Martínez");
        testUsuario.setRut("12345678-9");
        testUsuario.setCorreo("carlos.lopez@duoc.cl");
    }

    @Test
    void getAllUsuarios() throws Exception {
        List<Usuario> usuarios = Arrays.asList(testUsuario);
        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Carlos López"));
    }

    @Test
    void getUsuarioById() throws Exception {
        when(usuarioService.getAllUsuario(1)).thenReturn(testUsuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Carlos López"))
                .andExpect(jsonPath("$.rut").value("12345678-9"));
    }

    @Test
    void createUsuario() throws Exception {
        String expectedResponse = "Usuario creado exitosamente";
        when(usuarioService.addUsuario(any(Usuario.class))).thenReturn(expectedResponse);
        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUsuario)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deleteUsuario() throws Exception {
        String expectedResponse = "Usuario eliminado";
        when(usuarioService.deleteUsuario(1)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
