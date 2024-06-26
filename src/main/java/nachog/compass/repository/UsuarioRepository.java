package nachog.compass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import nachog.compass.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUniversidadSeleccionadaIdUniversidad(Long universidadId);
}