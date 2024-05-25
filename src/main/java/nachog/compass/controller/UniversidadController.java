package nachog.compass.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nachog.compass.entity.Universidad;
import nachog.compass.repository.UniversidadRepository;

import java.util.List;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

    private final UniversidadRepository universidadRepository;

    public UniversidadController(UniversidadRepository universidadRepository) {
        this.universidadRepository = universidadRepository;
    }

    @GetMapping
    public List<Universidad> getAllUniversidades() {
        return universidadRepository.findAll();
    }

    @GetMapping("/{id}")
    public Universidad getUniversidadById(@PathVariable Long id) {
        return universidadRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Universidad createUniversidad(@RequestBody Universidad universidad) {
        return universidadRepository.save(universidad);
    }

    @PutMapping("/{id}")
    public Universidad updateUniversidad(@PathVariable Long id, @RequestBody Universidad universidadDetails) {
        Universidad universidad = universidadRepository.findById(id).orElse(null);
        if (universidad != null) {
            universidad.setNombre(universidadDetails.getNombre());
            return universidadRepository.save(universidad);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUniversidad(@PathVariable Long id) {
        universidadRepository.deleteById(id);
    }
}