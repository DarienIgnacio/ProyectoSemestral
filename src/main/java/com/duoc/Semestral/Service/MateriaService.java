package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Materia;
import com.duoc.Semestral.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> getMaterias() {
        return materiaRepository.findAll();
    }

    public Materia getMateria(int id) {
        return materiaRepository.findById(id).orElse(null);
    }

    public String addMateria(Materia materia) {
        materiaRepository.save(materia);
        return "Materia adicionado con exito";
    }

    public String updateMateria(Materia materia) {
        materiaRepository.save(materia);
        return "Materia actualizado con exito";
    }

    public String deleteMateria(int id) {
        materiaRepository.deleteById(id);
        return "Materia eliminada";
    }

}
