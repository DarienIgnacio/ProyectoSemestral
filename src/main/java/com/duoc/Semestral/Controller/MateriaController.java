package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Materia;
import com.duoc.Semestral.Service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> getMaterias(){
        return materiaService.getMaterias();
    }

    @GetMapping("/{id")
    public Materia getMateria(@PathVariable int id){
        return materiaService.getMateria(id);
    }

    @PostMapping
    public String addMateria(@RequestBody Materia materia){
        return materiaService.addMateria(materia);
    }

    @DeleteMapping("/{id}")
    public String deleteMateria(@PathVariable int id){
        return materiaService.deleteMateria(id);
    }

    @PutMapping("/{id}")
    public String updateMateria(@PathVariable int id, @RequestBody Materia materia){
        return materiaService.updateMateria(materia);
    }

}
