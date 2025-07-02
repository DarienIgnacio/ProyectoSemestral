package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.InscripcionesController;
import com.duoc.Semestral.Model.Inscripciones;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class InscripcionesModelAssembler implements RepresentationModelAssembler<Inscripciones, EntityModel<Inscripciones>> {

    @Override
    public EntityModel<Inscripciones> toModel(Inscripciones inscripciones) {

        return EntityModel.of(inscripciones,
                linkTo(methodOn(InscripcionesController.class).getInscripcionById(inscripciones.getIdInscripcion())).withSelfRel(),

                linkTo(methodOn(InscripcionesController.class).getAllInscripciones()).withRel("GET")
        );
    }
    
}

