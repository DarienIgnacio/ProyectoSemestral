package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Alumno;
import com.duoc.Semestral.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class AlumnoService {
    @Autowired
    AlumnoRepository AlumnoRepository;

    public List<Alumno> getAllAlumnos(){return AlumnoRepository.getAllAlumnos();}

    public Alumno getAlumnoById(int id){return AlumnoRepository.getAlumnoById(id);}

    public String addAlumno(Alumno alumno){return AlumnoRepository.addAlumno(alumno);}

    public String deleteAlumno(int id){return AlumnoRepository.deleteAlumno(id);}

    public String updateAlumno(Alumno a){return AlumnoRepository.updateAlumno(a);}
}