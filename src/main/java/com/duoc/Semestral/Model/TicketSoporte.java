package com.duoc.Semestral.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketSoporte {
    private int idSoporte;
    private String usuarioTicket;
    private String nombreTicket;
    private String descripcionTicket;
}
