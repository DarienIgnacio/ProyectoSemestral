package com.duoc.Semestral.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
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
