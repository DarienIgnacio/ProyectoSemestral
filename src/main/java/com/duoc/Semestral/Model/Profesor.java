package com.duoc.Semestral.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="id")

public class Profesor {
    private String nombre;
    @Id
    private int id;
    private String correo;
}
