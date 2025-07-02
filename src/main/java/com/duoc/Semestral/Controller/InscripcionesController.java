package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Assembler.InscripcionesModelAssembler;
import com.duoc.Semestral.Model.Inscripciones;
import com.duoc.Semestral.Service.InscripcionesService;
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

@RestController
@RequestMapping("/inscripciones")
@Tag(name = "Inscripciones", description = "API para gestión de inscripciones de alumnos a cursos")
public class InscripcionesController {
    private final InscripcionesService inscripcionesService;

    @Autowired
    private InscripcionesModelAssembler assembler;


    public InscripcionesController(InscripcionesService inscripcionesService) {
        this.inscripcionesService = inscripcionesService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las inscripciones", description = "Retorna una lista con todas las inscripciones registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inscripciones obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public CollectionModel<EntityModel<Inscripciones>> getAllInscripciones() {
        List<Inscripciones> inscripciones = inscripcionesService.getAllInscripciones();
        
        return assembler.toCollectionModel(inscripciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inscripción por ID", description = "Retorna una inscripción específica basada en su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripción encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inscripción no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public EntityModel<Inscripciones> getInscripcionById(
            @Parameter(description = "ID único de la inscripción", required = true) @PathVariable int id) {
        Inscripciones inscripcion = inscripcionesService.getInscripcionById(id);
        return assembler.toModel(inscripcion);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear nueva inscripción", description = "Registra una nueva inscripción de alumno a curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inscripción creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "El alumno ya está inscrito en este curso"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public String addInscripcion(
            @Parameter(description = "Datos de la inscripción a crear", required = true) @RequestBody Inscripciones inscripciones) {
        return inscripcionesService.addInscripcion(inscripciones);
    }

    @PutMapping
    @Operation(summary = "Actualizar inscripción", description = "Actualiza los datos de una inscripción existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripción actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inscripción no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public String updateInscripcion(
            @Parameter(description = "Datos actualizados de la inscripción", required = true) @RequestBody Inscripciones inscripciones) {
        return inscripcionesService.updateInscripcion(inscripciones);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inscripción", description = "Elimina una inscripción del sistema basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscripción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inscripción no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public String deleteInscripcionById(
            @Parameter(description = "ID único de la inscripción a eliminar", required = true) @PathVariable int id) {
        return inscripcionesService.deleteInscripcion(id);
    }

}
