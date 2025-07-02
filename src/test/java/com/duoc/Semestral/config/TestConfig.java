package com.duoc.Semestral.config;

import com.duoc.Semestral.Repository.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public AlumnoRepository alumnoRepository() {
        return mock(AlumnoRepository.class);
    }

    @Bean
    @Primary
    public CursoRepository cursoRepository() {
        return mock(CursoRepository.class);
    }

    @Bean
    @Primary
    public InscripcionRepository inscripcionRepository() {
        return mock(InscripcionRepository.class);
    }

    @Bean
    @Primary
    public MateriaRepository materiaRepository() {
        return mock(MateriaRepository.class);
    }

    @Bean
    @Primary
    public ProfesorRepository profesorRepository() {
        return mock(ProfesorRepository.class);
    }

    @Bean
    @Primary
    public SoporteRepository soporteRepository() {
        return mock(SoporteRepository.class);
    }

    @Bean
    @Primary
    public UsuarioRepository usuarioRepository() {
        return mock(UsuarioRepository.class);
    }
}
