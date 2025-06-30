package com.duoc.Semestral.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
    
public class Soporte extends Usuario{
    private List<Usuario> ticketUsuario = new ArrayList<>();
    private String Departemento;
    @Id
    private int id;

}
