package com.duoc.Semestral.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Soporte extends Usuario{
    private List<TicketSoporte> ticketsAsignados = new ArrayList<>();
    private String Departemento;
    private int id;

}
