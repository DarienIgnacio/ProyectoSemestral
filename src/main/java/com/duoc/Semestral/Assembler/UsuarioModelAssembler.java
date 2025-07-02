package com.duoc.Semestral.Assembler;

import com.duoc.Semestral.Controller.UsuarioController;
import com.duoc.Semestral.Model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {


    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuario(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getUsuarios()).withRel("GET")
        );
    }
}

