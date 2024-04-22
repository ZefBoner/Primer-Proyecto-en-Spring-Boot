package com.example.primerspringapis.controller;

import com.example.primerspringapis.model.Usuario;
import com.example.primerspringapis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioNuevo) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(usuarioNuevo.getNombre());
        usuario.setEmail(usuarioNuevo.getEmail());
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarUsuariosPorNombre(@RequestParam String nombre) {
        return usuarioRepository.findByNombreContaining(nombre);
    }

    @GetMapping("/count")
    public long contarUsuarios() {
        return usuarioRepository.count();
    }
}