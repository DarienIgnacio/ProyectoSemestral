package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Assembler.CursoModelAssembler;
import com.duoc.Semestral.Model.Curso;
import com.duoc.Semestral.Service.CursoService;
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
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "API para gestión de cursos académicos")
public class CursoController {
        @Autowired
        private CursoService cursoService;

        @Autowired
        private CursoModelAssembler assembler;

        @GetMapping
        @Operation(summary = "Obtener todos los cursos", description = "Retorna una lista con todos los cursos disponibles en el sistema con enlaces HATEOAS")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public CollectionModel<EntityModel<Curso>> getCursos() {
                List<Curso> cursos = cursoService.getCursos();

                return assembler.toCollectionModel(cursos);
                
                
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener curso por ID", description = "Retorna un curso específico basado en su identificador único con enlaces HATEOAS")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Curso encontrado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public EntityModel<Curso> getCurso(
                        @Parameter(description = "ID único del curso", required = true) @PathVariable int id) {
                Curso curso = cursoService.getCursoById(id);
                return assembler.toModel(curso);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Crear nuevo curso", description = "Registra un nuevo curso en el sistema académico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public Curso addCurso(
                        @Parameter(description = "Datos del curso a crear", required = true) @RequestBody Curso curso) {
                return cursoService.saveCurso(curso);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar curso", description = "Elimina un curso del sistema basado en su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Curso eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String deleteCurso(
                        @Parameter(description = "ID único del curso a eliminar", required = true) @PathVariable int id) {
                return cursoService.deleteCurso(id);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar curso", description = "Actualiza los datos de un curso existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public Curso updateCurso(
                        @Parameter(description = "ID del curso a actualizar", required = true) @PathVariable int id,
                        @Parameter(description = "Datos actualizados del curso", required = true) @RequestBody Curso curso) {
                return cursoService.updateCurso(curso);
        }
}
