package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Alumno;
import com.duoc.Semestral.Service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/alumnos")

public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> getAlumnos(){
        return alumnoService.getAlumnos();
    }
    @GetMapping("/{id}")
    public Alumno getAlumno(@PathVariable int id) {
        return alumnoService.getAlumno(id);
    }

    @PostMapping
    public String createAlumno(@RequestBody Alumno alumno) {
        return alumnoService.addAlumno(alumno);
    }

    @DeleteMapping("/{id}")
    public String deleteAlumno(@PathVariable int id) {
        return alumnoService.deleteAlumno(id);
    }

    @PutMapping("/{id}")
    public String updateAlumno(@RequestBody Alumno alumno) {
        return alumnoService.updateAlumno(alumno);
    }
}
