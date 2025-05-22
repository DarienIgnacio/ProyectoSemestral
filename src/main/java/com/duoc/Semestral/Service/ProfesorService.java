package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Profesor;
import com.duoc.Semestral.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> getProfesores() {
        return profesorRepository.findAll();
    }

    public Profesor getProfesor(int id) {
        return profesorRepository.getById(id);
    }

    public String addProfesor(Profesor profesor) {
        profesorRepository.save(profesor);
        return profesor.toString();
    }

    public String deleteProfesor(int id) {
        profesorRepository.deleteById(id);
        return "Profesor eliminado con exito!";
    }

    public String updateProfesor(Profesor profesor) {
        profesorRepository.save(profesor);
        return "Profesor actualizado con exito!";
    }
}
