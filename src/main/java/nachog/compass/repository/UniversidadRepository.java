package nachog.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nachog.compass.entity.Universidad;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Long> {
}