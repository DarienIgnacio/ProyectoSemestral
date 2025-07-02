package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Assembler.AlumnoModelAssembler;
import com.duoc.Semestral.Model.Alumno;
import com.duoc.Semestral.Service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/alumnos")
@Tag(name = "Alumnos", description = "API para gestión de alumnos")
public class AlumnoController {
        @Autowired
        AlumnoService alumnoService;

        @Autowired
        AlumnoModelAssembler assembler;

        @GetMapping
        @Operation(summary = "Obtener todos los alumnos", description = "Retorna una lista con todos los alumnos registrados en el sistema con enlaces HATEOAS")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida exitosamente"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public CollectionModel<EntityModel<Alumno>> getAlumnos() {
                List<Alumno> alumnos = alumnoService.getAlumnos();

                return assembler.toCollectionModel(alumnos);
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener alumno por ID", description = "Retorna un alumno específico basado en su identificador único con enlaces HATEOAS para navegación")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Alumno encontrado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public EntityModel<Alumno> getAlumno(
                        @Parameter(description = "ID único del alumno", required = true) @PathVariable int id) {
                Alumno alumno = alumnoService.getAlumno(id);
                return assembler.toModel(alumno);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Crear nuevo alumno", description = "Registra un nuevo alumno en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Alumno creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String createAlumno(
                        @Parameter(description = "Datos del alumno a crear", required = true) @RequestBody Alumno alumno) {
                return alumnoService.addAlumno(alumno);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar alumno", description = "Elimina un alumno del sistema basado en su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Alumno eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String deleteAlumno(
                        @Parameter(description = "ID único del alumno a eliminar", required = true) @PathVariable int id) {
                return alumnoService.deleteAlumno(id);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar alumno", description = "Actualiza los datos de un alumno existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Alumno actualizado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String updateAlumno(
                        @Parameter(description = "ID del alumno a actualizar", required = true) @PathVariable int id,
                        @Parameter(description = "Datos actualizados del alumno", required = true) @RequestBody Alumno alumno) {
                return alumnoService.updateAlumno(alumno);
        }
}
