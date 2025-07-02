package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Assembler.ProfesorModelAssembler;
import com.duoc.Semestral.Model.Profesor;
import com.duoc.Semestral.Service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/profesores")
@Tag(name = "Profesores", description = "API para gestión de profesores")
public class ProfesorController {
        @Autowired
        ProfesorService profesorService;

        @Autowired
        ProfesorModelAssembler assembler;

        @GetMapping
        @Operation(summary = "Obtener todos los profesores", description = "Retorna una lista con todos los profesores registrados en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de profesores obtenida exitosamente"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public CollectionModel<EntityModel<Profesor>> getProfesores() {
                List<Profesor> profesores = profesorService.getProfesores();

                return assembler.toCollectionModel(profesores);
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener profesor por ID", description = "Retorna un profesor específico basado en su identificador único")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Profesor encontrado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public EntityModel<Profesor> getProfesor(
                        @Parameter(description = "ID único del profesor", required = true) @PathVariable int id) {
                Profesor profesor = profesorService.getProfesor(id);
                return assembler.toModel(profesor);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Crear nuevo profesor", description = "Registra un nuevo profesor en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Profesor creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String addProfesor(
                        @Parameter(description = "Datos del profesor a crear", required = true) @RequestBody Profesor profesor) {
                return profesorService.addProfesor(profesor);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar profesor", description = "Elimina un profesor del sistema basado en su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Profesor eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String deleteProfesor(
                        @Parameter(description = "ID único del profesor a eliminar", required = true) @PathVariable int id) {
                return profesorService.deleteProfesor(id);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar profesor", description = "Actualiza los datos de un profesor existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String updateProfesor(
                        @Parameter(description = "ID del profesor a actualizar", required = true) @PathVariable int id,
                        @Parameter(description = "Datos actualizados del profesor", required = true) @RequestBody Profesor profesor) {
                return profesorService.updateProfesor(profesor);
        }
}
