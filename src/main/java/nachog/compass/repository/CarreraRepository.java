package nachog.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nachog.compass.entity.Carrera;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT c FROM Carrera c WHERE c.ofertaAcademica.id_oferta_academica = :idOfertaAcademica")
    List<Carrera> findByOfertaAcademicaId(@Param("idOfertaAcademica") Long idOfertaAcademica);
}