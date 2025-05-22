package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Inscripciones;
import com.duoc.Semestral.Service.InscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionesController {
    @Autowired
    InscripcionesService inscripcionesService;

    @GetMapping
    public List<Inscripciones> getAllInscripciones() {
        return inscripcionesService.getAllInscripciones();
    }

    @GetMapping
    public Inscripciones getInscripcionById(@RequestParam int idInscripcion) {
        return inscripcionesService.getInscripcionById(idInscripcion);
    }

    @PostMapping
    public String addInscripcion(@RequestBody Inscripciones inscripciones) {
        return inscripcionesService.addInscripcion(inscripciones);
    }

    @PutMapping
    public String updateInscripcion(@RequestBody Inscripciones inscripciones) {
        return inscripcionesService.updateInscripcion(inscripciones);
    }

    @DeleteMapping
    public String deleteInscripcionById(@RequestParam int idInscripcion) {
        return inscripcionesService.deleteInscripcion(idInscripcion);
    }


}
