package nachog.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nachog.compass.entity.CarreraSeleccionada;
import nachog.compass.entity.CarreraSeleccionadaKey;

public interface CarreraSeleccionadaRepository extends JpaRepository<CarreraSeleccionada, CarreraSeleccionadaKey> {
}