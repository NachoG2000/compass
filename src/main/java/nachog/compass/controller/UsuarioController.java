package nachog.compass.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import nachog.compass.entity.Universidad;
import nachog.compass.entity.Usuario;
import nachog.compass.repository.UniversidadRepository;
import nachog.compass.service.UsuarioService;
import nachog.compass.DTO.UsuarioResponseDTO;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UniversidadRepository universidadRepository;

    public UsuarioController(UsuarioService usuarioService, UniversidadRepository universidadRepository) {
        this.usuarioService = usuarioService;
        this.universidadRepository = universidadRepository;
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok().body(newUsuario);
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
        return ResponseEntity.ok().body(updatedUsuario);
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @GetMapping("/universidad/{universidadId}")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuariosPorUniversidadId(@PathVariable Long universidadId) {
        Universidad universidad = universidadRepository.findById(universidadId).orElse(null);
        if (universidad != null) {
            List<UsuarioResponseDTO> usuariosDTO = usuarioService.getUsuariosPorUniversidadId(universidadId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
            ;
            return ResponseEntity.ok(usuariosDTO);
        }

        return ResponseEntity.notFound().build();
    }

    private UsuarioResponseDTO convertToDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDTO.setGenero(usuario.getGenero());
        return usuarioDTO;
    }
}

    // @GetMapping
    // public ResponseEntity<List<Usuario>> getAllUsuarios() {
    //     List<Usuario> usuarios = usuarioService.findAllUsuarios();
    //     return ResponseEntity.ok().body(usuarios);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
    //     Usuario usuario = usuarioService.findUsuarioById(id);
    //     return ResponseEntity.ok().body(usuario);
    // 