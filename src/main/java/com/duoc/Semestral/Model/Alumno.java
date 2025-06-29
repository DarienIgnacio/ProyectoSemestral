package com.duoc.Semestral.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alumno {
    private String nombre;
    @id
    private int id;
    private String correo;

}
