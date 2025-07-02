package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Curso;
import com.duoc.Semestral.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(int id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public String deleteCurso(int id) {
        cursoRepository.deleteById(id);
        return "Curso Eliminado";
    }
}
