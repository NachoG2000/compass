package nachog.compass.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nachog.compass.entity.Carrera;
import nachog.compass.entity.OfertaAcademica;
import nachog.compass.repository.CarreraRepository;
import nachog.compass.repository.OfertaAcademicaRepository;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    private CarreraRepository carreraRepository;
    private OfertaAcademicaRepository ofertaAcademicaRepository;

    public CarreraController(CarreraRepository carreraRepository, OfertaAcademicaRepository ofertaAcademicaRepository) {
        this.carreraRepository = carreraRepository;
        this.ofertaAcademicaRepository = ofertaAcademicaRepository;
    }

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Carrera getCarreraById(@PathVariable Long id) {
        return carreraRepository.findById(id).orElse(null);
    }

    @PostMapping("/oferta/{ofertaId}")
    public Carrera createCarrera(@PathVariable Long ofertaId, @RequestBody Carrera carrera) {
        OfertaAcademica ofertaAcademica = ofertaAcademicaRepository.findById(ofertaId).orElse(null);
        if (ofertaAcademica != null) {
            carrera.setOfertaAcademica(ofertaAcademica);
            return carreraRepository.save(carrera);
        }
        return null;
    }

    @PutMapping("/{id}")
    public Carrera updateCarrera(@PathVariable Long id, @RequestBody Carrera carreraDetails) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if (carrera != null) {
            carrera.setNombre_carrera(carreraDetails.getNombre_carrera());
            carrera.setDescripcion_carrera(carreraDetails.getDescripcion_carrera());
            carrera.setDuracion_carrera(carreraDetails.getDuracion_carrera());
            carrera.setLink_web_carrera(carreraDetails.getLink_web_carrera());
            return carreraRepository.save(carrera);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCarrera(@PathVariable Long id) {
        carreraRepository.deleteById(id);
    }
}