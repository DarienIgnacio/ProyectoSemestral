package com.duoc.Semestral.Model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Curso {
    private String Nombre;
    private int Id;
    private List<Alumno> listaCurso;
    private List<Materia> listaMateria;
}
