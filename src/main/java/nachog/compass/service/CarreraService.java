package nachog.compass.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import nachog.compass.entity.Carrera;
import nachog.compass.entity.CarreraSeleccionada;
import nachog.compass.entity.CarreraSeleccionadaKey;
import nachog.compass.entity.Usuario;
import nachog.compass.repository.CarreraRepository;
import nachog.compass.repository.CarreraSeleccionadaRepository;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;
    private final CarreraSeleccionadaRepository carreraSeleccionadaRepository;

    public CarreraService(CarreraRepository carreraRepository, CarreraSeleccionadaRepository carreraSeleccionadaRepository) {
        this.carreraRepository = carreraRepository;
        this.carreraSeleccionadaRepository = carreraSeleccionadaRepository;
    }

    public String getCarrerasDisponiblesString(Long idOfertaAcademica) {
        List<Carrera> carreras = carreraRepository.findByOfertaAcademicaId(idOfertaAcademica);

        return carreras.stream()
                .map(carrera -> carrera.getId() + " - " + carrera.getNombre_carrera())
                .collect(Collectors.joining("\n"));
    }

    public List<Carrera> getCarrerasByIds(List<Long> ids) {
        return carreraRepository.findAllById(ids);
    }

    public Carrera getCarreraById(Long id) {
        Optional<Carrera> carrera = carreraRepository.findById(id);
        return carrera.orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    public void saveCarrerasSeleccionadas(Usuario usuario , List<Carrera> carreras) {
        for (Long carreraId : carreras.stream().map(Carrera::getId).collect(Collectors.toList())) {
            CarreraSeleccionadaKey carreraSeleccionadaKey = new CarreraSeleccionadaKey();
            carreraSeleccionadaKey.setIdCarrera(carreraId);
            carreraSeleccionadaKey.setIdUsuario(usuario.getIdUsuario());

            CarreraSeleccionada carreraSeleccionada = new CarreraSeleccionada();
            carreraSeleccionada.setId(carreraSeleccionadaKey);
            carreraSeleccionada.setCarrera(getCarreraById(carreraId)); // Debes crear este método en CarreraService
            carreraSeleccionada.setUsuario(usuario);

            carreraSeleccionadaRepository.save(carreraSeleccionada);
        }
    }
}