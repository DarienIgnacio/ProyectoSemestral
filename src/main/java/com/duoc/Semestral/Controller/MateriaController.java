package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Assembler.MateriaModelAssembler;
import com.duoc.Semestral.Model.Materia;
import com.duoc.Semestral.Service.MateriaService;
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
@RequestMapping("/materias")
@Tag(name = "Materias", description = "API para gestión de materias académicas")
public class MateriaController {
        @Autowired
        private MateriaService materiaService;

        @Autowired
        private MateriaModelAssembler assembler;


        @GetMapping
        @Operation(summary = "Obtener todas las materias", description = "Retorna una lista con todas las materias disponibles en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de materias obtenida exitosamente"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public CollectionModel<EntityModel<Materia>> getMaterias() {
                List<Materia> materias = materiaService.getMaterias();

                return assembler.toCollectionModel(materias);
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener materia por ID", description = "Retorna una materia específica basada en su identificador único")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Materia encontrada exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Materia no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public EntityModel<Materia> getMateria(
                        @Parameter(description = "ID único de la materia", required = true) @PathVariable int id) {
                return assembler.toModel(materiaService.getMateria(id));
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Crear nueva materia", description = "Registra una nueva materia en el sistema académico")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Materia creada exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String addMateria(
                        @Parameter(description = "Datos de la materia a crear", required = true) @RequestBody Materia materia) {
                return materiaService.addMateria(materia);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar materia", description = "Elimina una materia del sistema basada en su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Materia eliminada exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Materia no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String deleteMateria(
                        @Parameter(description = "ID único de la materia a eliminar", required = true) @PathVariable int id) {
                return materiaService.deleteMateria(id);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar materia", description = "Actualiza los datos de una materia existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Materia actualizada exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Materia no encontrada"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String updateMateria(
                        @Parameter(description = "ID de la materia a actualizar", required = true) @PathVariable int id,
                        @Parameter(description = "Datos actualizados de la materia", required = true) @RequestBody Materia materia) {
                return materiaService.updateMateria(materia);
        }

}
