package com.duoc.Semestral.Repository;

import com.duoc.proyect.Model.Materia;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MateriaRepository {

    private List<Materia> materias = new ArrayList<>();

    public MateriaRepository() {
        materias.add(new Materia("1", "testMateria1", "testDescription1", "testFile1.jpg"));
        materias.add(new Materia("2", "testMateria2", "testDescription2", "testFile2.jpg"));
        materias.add(new Materia("3", "testMaterias3", "testDescription3", "testFile3.jpg"));

    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public Materia getMateriaById(String id) {
        for (Materia materia : materias) {
            if (materia.getId().equals(id)) {
                return materia;
            }
        }
        return null;
    }

    public String addMateria(Materia materia) {
        materias.add(materia);
        return "materia agregado" + materia.toString();
    }

    public String deleteMateria(String id) {
        for (Materia c : materias) {
            if (c.getId().equals(id)) {
                materias.remove(c);
                return "materia eliminado \n" +c.toString();
            }
        }
        return "Materia not found";
    }

    public String updateMateria(Materia materia) {
        for (Materia materia2 : materias) {
            if (materia2.getId().equals(materia.getId())) {
                materia2.setNombre(materia.getNombre());
                materia2.setDescripcion(materia.getDescripcion());
                materia2.setArchivo(materia.getArchivo());
                return materia2 + "updated";
            }
        }
        return materia + "not found";
    }



}