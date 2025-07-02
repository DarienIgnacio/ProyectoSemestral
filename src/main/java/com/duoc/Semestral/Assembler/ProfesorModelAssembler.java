package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.ProfesorController;
import com.duoc.Semestral.Model.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>>{
    @Override
    public EntityModel<Profesor> toModel(Profesor profesor) {
        return EntityModel.of(profesor,
                linkTo(methodOn(ProfesorController.class).getProfesor(profesor.getId())).withSelfRel(),
                linkTo(methodOn(ProfesorController.class).getProfesores()).withRel("GET")
        );
    }

}