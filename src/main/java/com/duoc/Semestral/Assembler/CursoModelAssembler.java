package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.CursoController;
import com.duoc.Semestral.Model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCurso(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).getCursos()).withRel("GET")
        );
    }
}
