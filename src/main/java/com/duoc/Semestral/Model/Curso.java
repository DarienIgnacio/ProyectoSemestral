package com.duoc.Semestral.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {
    private String nombre;
    @Id
    private int id;

    @OneToMany
    @JoinColumn(name = "curso_id")
    private List<Alumno> listaCurso;

    @OneToMany
    @JoinColumn(name = "curso_id")
    private List<Materia> listaMateria;
}
