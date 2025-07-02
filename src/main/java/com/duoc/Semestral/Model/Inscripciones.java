package com.duoc.Semestral.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Inscripciones {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idInscripcion;
    private String cursoInscrito;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;
    private Date fechaInscrito;
    private Date fechaFinalización;
}
