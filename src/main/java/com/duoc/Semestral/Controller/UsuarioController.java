package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Usuario;
import com.duoc.Semestral.Service.UsuarioService;
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
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "API para gestión de usuarios del sistema")
public class UsuarioController {
        @Autowired
        UsuarioService usuarioService;

        @GetMapping
        @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public List<Usuario> getUsuarios() {
                return usuarioService.getAllUsuarios();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario específico basado en su identificador único")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public Usuario getUsuario(
                        @Parameter(description = "ID único del usuario", required = true) @PathVariable int id) {
                return usuarioService.getAllUsuario(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Crear nuevo usuario", description = "Registra un nuevo usuario en el sistema")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "409", description = "Usuario ya existe"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String createUsuario(
                        @Parameter(description = "Datos del usuario a crear", required = true) @RequestBody Usuario usuario) {
                return usuarioService.addUsuario(usuario);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema basado en su ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String deleteUsuario(
                        @Parameter(description = "ID único del usuario a eliminar", required = true) @PathVariable int id) {
                return usuarioService.deleteUsuario(id);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public String updateUsuario(
                        @Parameter(description = "ID del usuario a actualizar", required = true) @PathVariable int id,
                        @Parameter(description = "Datos actualizados del usuario", required = true) @RequestBody Usuario usuario) {
                return usuarioService.updateUsuario(usuario);
        }
}
