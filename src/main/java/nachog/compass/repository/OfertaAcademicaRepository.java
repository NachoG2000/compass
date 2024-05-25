package nachog.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nachog.compass.entity.OfertaAcademica;

@Repository
public interface OfertaAcademicaRepository extends JpaRepository<OfertaAcademica, Long> {
}