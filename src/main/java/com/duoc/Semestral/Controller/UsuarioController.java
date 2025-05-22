package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Usuario;
import com.duoc.Semestral.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }


    @GetMapping
    public Usuario getUsuario(@RequestParam int id) {
        return usuarioService.getAllUsuario(id);
    }

    @PostMapping
    public String createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);

    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable int id) {
        return usuarioService.deleteUsuario(id);
    }

    @PostMapping("/{id}")
    public String updateUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }
}
