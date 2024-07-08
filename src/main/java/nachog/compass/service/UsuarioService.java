package nachog.compass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nachog.compass.entity.Usuario;
import nachog.compass.repository.CarreraSeleccionadaRepository;
import nachog.compass.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CarreraSeleccionadaRepository carreraSeleccionadaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, CarreraSeleccionadaRepository carreraSeleccionadaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.carreraSeleccionadaRepository = carreraSeleccionadaRepository;
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

    @Transactional
    public void deleteUsuario(Long id) {
        // Eliminar registros relacionados en carrera_seleccionada
        carreraSeleccionadaRepository.deleteByIdIdUsuario(id);

        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> getUsuariosPorUniversidadId(Long universidadId) {
        return usuarioRepository.findByUniversidadSeleccionadaIdUniversidad(universidadId);
    }
}