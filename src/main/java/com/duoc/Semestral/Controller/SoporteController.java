package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Soporte;
import com.duoc.Semestral.Service.SoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soportes")
@Tag(name = "Soporte", description = "API para gestión de tickets y soporte técnico")
public class SoporteController {
        @Autowired
        private SoporteService soporteService;

        @GetMapping
        @Operation(summary = "Obtener todos los soportes", description = "Retorna una lista con todos los tickets de soporte registrados en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de soportes obtenida exitosamente"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public List<Soporte> getSoportes() {
                return soporteService.getSoportes();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener soporte por ID", description = "Retorna un ticket de soporte específico basado en su identificador único")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Soporte encontrado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Soporte no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public Soporte getSoporteById(
                        @Parameter(description = "ID único del ticket de soporte", required = true) @PathVariable int id) {
                return soporteService.getSoporteById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Crear nuevo ticket de soporte", description = "Registra un nuevo ticket de soporte en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Ticket de soporte creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String createSoporte(
                        @Parameter(description = "Datos del ticket de soporte a crear", required = true) @RequestBody Soporte soporte) {
                return soporteService.addSoporte(soporte);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar ticket de soporte", description = "Actualiza los datos de un ticket de soporte existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Ticket de soporte actualizado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Soporte no encontrado"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String updateSoporte(
                        @Parameter(description = "ID del ticket de soporte a actualizar", required = true) @PathVariable int id,
                        @Parameter(description = "Datos actualizados del ticket de soporte", required = true) @RequestBody Soporte soporte) {
                return soporteService.updateSoporte(soporte);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar ticket de soporte", description = "Elimina un ticket de soporte del sistema basado en su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Ticket de soporte eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Soporte no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String deleteSoporte(
                        @Parameter(description = "ID único del ticket de soporte a eliminar", required = true) @PathVariable int id) {
                return soporteService.deleteSoporte(id);
        }
}
