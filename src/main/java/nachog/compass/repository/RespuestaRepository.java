package nachog.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nachog.compass.entity.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}