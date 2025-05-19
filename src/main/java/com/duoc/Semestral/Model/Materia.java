package com.duoc.Semestral.Model;
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

public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombreMateria;
    private String descripcionMateria;
    private int idMateria;
    private String archivoMateria;
}
