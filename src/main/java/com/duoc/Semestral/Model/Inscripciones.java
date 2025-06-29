package com.duoc.Semestral.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
    
public class Inscripciones {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int idInscripcion;
    private String cursoInscrito;
    private Alumno alumno;
    private Date fechaInscrito;
    private Date fechaFinalización;
}
