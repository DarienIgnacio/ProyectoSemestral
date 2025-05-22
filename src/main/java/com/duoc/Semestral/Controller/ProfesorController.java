package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Profesor;
import com.duoc.Semestral.Service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping
    public List<Profesor> getProfesores() {
        return profesorService.getProfesores();
    }

    @GetMapping("/{id}")
    public Profesor getProfesor(@PathVariable int id) {
        return profesorService.getProfesor(id);
    }

    @PostMapping
    public String addProfesor(@RequestBody Profesor profesor) {
        return profesorService.addProfesor(profesor);
    }

    @DeleteMapping("/{id}")
    public String deleteProfesor(@PathVariable int id) {
        return profesorService.deleteProfesor(id);
    }

    @PutMapping("/{id}")
    public String updateProfesor(@PathVariable int id, @RequestBody Profesor profesor) {
        return profesorService.updateProfesor(profesor);
    }
}
