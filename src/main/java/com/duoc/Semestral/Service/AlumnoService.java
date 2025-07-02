package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Alumno;
import com.duoc.Semestral.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno getAlumno(int id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public String addAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
        return "Alumno creado exitosamente: " + alumno.getNombre();
    }

    public String deleteAlumno(int id) {
        alumnoRepository.deleteById(id);
        return "Alumno eliminado";
    }

    public String updateAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
        return "Alumno actualizado";
    }
}