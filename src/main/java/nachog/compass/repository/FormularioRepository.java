package nachog.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nachog.compass.entity.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Long> {
    Formulario findByUniversidadIdUniversidad(Long universidadId);
}
