package com.duoc.Semestral.Model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Nombre;
    private int Id;
    private List<Alumno> listaCurso;
    private List<Materia> listaMateria;
}
