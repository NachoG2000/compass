package nachog.compass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nachog.compass.entity.Usuario;
import nachog.compass.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        // Usuario usuario = usuarioRepository.findById(id).orElse(null);
        // if (usuario != null) {
        //     usuario.setNombre(usuarioDetails.getNombre());
        //     usuario.setApellido(usuarioDetails.getApellido());
        //     usuario.setCorreo(usuarioDetails.getCorreo());
        //     usuario.setContrasena(usuarioDetails.getContrasena());
        //     usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
        //     usuario.setGenero(usuarioDetails.getGenero());
        //     usuario.setTelefono(usuarioDetails.getTelefono());
        //     usuario.setOfertaSeleccionada(usuarioDetails.getOfertaSeleccionada());
        //     return usuarioRepository.save(usuario);
        // }
        return null;
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> getUsuariosPorUniversidadId(Long universidadId) {
        return usuarioRepository.findByUniversidadSeleccionadaIdUniversidad(universidadId);
    }
}