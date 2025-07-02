package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.MateriaController;
import com.duoc.Semestral.Model.Materia;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MateriaModelAssembler implements RepresentationModelAssembler<Materia, EntityModel<Materia>> {
    @Override
    public EntityModel<Materia> toModel(Materia materia) {

        return EntityModel.of(materia,
                linkTo(methodOn(MateriaController.class).getMateria(materia.getIdMateria())).withSelfRel(),
                linkTo(methodOn(MateriaController.class).getMaterias()).withRel("GET")
        );
    }
}   





