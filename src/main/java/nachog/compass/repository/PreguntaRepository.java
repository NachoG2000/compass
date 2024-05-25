package nachog.compass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nachog.compass.entity.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{
    @Query("SELECT p FROM Pregunta p WHERE p.formulario.id_formulario = :idFormulario")
    List<Pregunta> findByFormularioId(@Param("idFormulario") Long idFormulario);
}
