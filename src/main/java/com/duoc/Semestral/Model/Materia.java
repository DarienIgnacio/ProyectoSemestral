package com.duoc.Semestral.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity

public class Materia {
    private String nombreMateria;
    private String descripcionMateria;
    @Id
    private int idMateria;
    private String archivoMateria;
}
