package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Curso;
import com.duoc.Semestral.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getCursos(){
        return cursoService.getCursos();
    }

    @GetMapping("/{idcurso}")
    public Curso getCurso(@PathVariable int idcurso){
        return cursoService.getCursoById(idcurso);
    }

    @PostMapping
    public Curso addCurso(@RequestBody Curso curso){
        return cursoService.saveCurso(curso);
    }

    @DeleteMapping("/{id}")
    public String deleteCurso(@PathVariable int id){
        return cursoService.deleteCurso(id);
    }

    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable int id, @RequestBody Curso curso){
        return cursoService.updateCurso(curso);
    }
}
