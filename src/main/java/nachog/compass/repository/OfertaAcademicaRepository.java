package nachog.compass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nachog.compass.entity.OfertaAcademica;

@Repository
public interface OfertaAcademicaRepository extends JpaRepository<OfertaAcademica, Long> {
    List<OfertaAcademica> findByUniversidadIdUniversidad(Long idUniversidad);
}