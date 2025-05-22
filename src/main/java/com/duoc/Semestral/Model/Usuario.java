package com.duoc.Semestral.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String rut;
    private String correo;
}
