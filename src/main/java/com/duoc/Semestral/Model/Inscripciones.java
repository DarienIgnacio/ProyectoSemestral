package com.duoc.Semestral.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Inscripciones {
    private int idInscripcion;
    private String cursoInscrito;
    private Alumno alumno;
    private Date fechaInscrito;
    private Date fechaFinalización;
}
