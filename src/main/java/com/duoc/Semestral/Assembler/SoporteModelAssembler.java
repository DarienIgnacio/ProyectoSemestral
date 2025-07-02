package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.SoporteController;
import com.duoc.Semestral.Model.Soporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SoporteModelAssembler implements RepresentationModelAssembler<Soporte, EntityModel<Soporte>> {

    @Override
    public EntityModel<Soporte> toModel(Soporte soporte) {
        return EntityModel.of(soporte,
                linkTo(methodOn(SoporteController.class).getSoporteById(soporte.getId())).withSelfRel(),
                linkTo(methodOn(SoporteController.class).getSoportes()).withRel("GET")
        );
    }
}