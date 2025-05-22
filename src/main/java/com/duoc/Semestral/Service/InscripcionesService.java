package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Inscripciones;
import com.duoc.Semestral.Repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionesService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripciones> getAllInscripciones(){
        return inscripcionRepository.findAll();
    }

    public Inscripciones getInscripcionById(int idInscripcion){
        return inscripcionRepository.getById(idInscripcion);
    }

    public String addInscripcion(Inscripciones inscripcion){
        inscripcionRepository.save(inscripcion);
        return "Inscripcion realizada!";
    }

    public String updateInscripcion(Inscripciones inscripcion) {
        inscripcionRepository.save(inscripcion);
        return "Inscripcion modificada!";
    }

    public String deleteInscripcion(int id){
        inscripcionRepository.deleteById(id);
        return "Inscripcion realizada!";
    }
}
