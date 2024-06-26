package nachog.compass.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nachog.compass.entity.Carrera;
import nachog.compass.entity.CarreraSeleccionada;
import nachog.compass.entity.OfertaAcademica;
import nachog.compass.repository.CarreraRepository;
import nachog.compass.repository.CarreraSeleccionadaRepository;
import nachog.compass.repository.OfertaAcademicaRepository;

@RestController
@RequestMapping("/carreras")
@CrossOrigin(origins = "http://localhost:5173")
public class CarreraController {

    private CarreraRepository carreraRepository;
    private CarreraSeleccionadaRepository carreraSeleccionadaRepository;
    private OfertaAcademicaRepository ofertaAcademicaRepository;

    public CarreraController(CarreraRepository carreraRepository, CarreraSeleccionadaRepository carreraSeleccionadaRepository ,OfertaAcademicaRepository ofertaAcademicaRepository) {
        this.carreraRepository = carreraRepository;
        this.carreraSeleccionadaRepository = carreraSeleccionadaRepository;
        this.ofertaAcademicaRepository = ofertaAcademicaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Carrera>> getAllCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        return ResponseEntity.ok(carreras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if (carrera != null) {
            return ResponseEntity.ok(carrera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carrera>> getCarrerasSeleeccionadasPorUsuarioId(@PathVariable Long usuarioId) {
        List<CarreraSeleccionada> carrerasSeleccionadas = carreraSeleccionadaRepository.findByIdIdUsuario(usuarioId);
        List<Carrera> carreras = carrerasSeleccionadas.stream()
                .map(CarreraSeleccionada::getCarrera)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carreras);
    }

    @PostMapping("/oferta/{ofertaId}")
    public ResponseEntity<Carrera> createCarrera(@PathVariable Long ofertaId, @RequestBody Carrera carrera) {
        OfertaAcademica ofertaAcademica = ofertaAcademicaRepository.findById(ofertaId).orElse(null);
        if (ofertaAcademica != null) {
            carrera.setOfertaAcademica(ofertaAcademica);
            Carrera savedCarrera = carreraRepository.save(carrera);
            return ResponseEntity.ok(savedCarrera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/oferta/{ofertaId}")
    public ResponseEntity<List<Carrera>> getCarrerasPorOfertaId(@PathVariable Long ofertaId) {
        OfertaAcademica ofertaAcademica = ofertaAcademicaRepository.findById(ofertaId).orElse(null);
        if (ofertaAcademica != null) {
            List<Carrera> carreras = carreraRepository.findByOfertaAcademicaId(ofertaId);
            return ResponseEntity.ok(carreras);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable Long id, @RequestBody Carrera carreraDetails) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if (carrera != null) {
            carrera.setNombre_carrera(carreraDetails.getNombre_carrera());
            carrera.setDescripcion_carrera(carreraDetails.getDescripcion_carrera());
            carrera.setDuracion_carrera(carreraDetails.getDuracion_carrera());
            carrera.setLink_web_carrera(carreraDetails.getLink_web_carrera());
            Carrera updatedCarrera = carreraRepository.save(carrera);
            return ResponseEntity.ok(updatedCarrera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
        carreraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}