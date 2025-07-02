package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.AlumnoController;
import com.duoc.Semestral.Model.Alumno;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AlumnoModelAssembler implements RepresentationModelAssembler<Alumno, EntityModel<Alumno>> {

    @Override
    public EntityModel<Alumno> toModel(Alumno alumno) {
        return EntityModel.of(alumno,
                linkTo(methodOn(AlumnoController.class).getAlumno(alumno.getId())).withSelfRel(),
                linkTo(methodOn(AlumnoController.class).getAlumnos()).withRel("GET")

        );
    }
}