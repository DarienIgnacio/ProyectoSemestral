package com.duoc.Semestral.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Soporte extends Usuario {
    @OneToMany
    @JoinColumn(name = "soporte_id")
    private List<Usuario> ticketUsuario = new ArrayList<>();
    private String departamento;

}
