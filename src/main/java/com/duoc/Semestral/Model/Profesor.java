package com.duoc.Semestral.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Profesor {
    private String nombre;
    private int id;
    private String correo;
}
