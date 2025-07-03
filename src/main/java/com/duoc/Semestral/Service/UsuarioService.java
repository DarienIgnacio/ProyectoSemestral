package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Usuario;
import com.duoc.Semestral.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getAllUsuario(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario getUsuario(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public String addUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario creado con exito!";
    }

    public String updateUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario actualizado con exito!";
    }

    public String deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
        return "Usuario eliminado";
    }
}
