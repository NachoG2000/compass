package nachog.compass.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nachog.compass.entity.Universidad;
import nachog.compass.repository.UniversidadRepository;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

    private final UniversidadRepository universidadRepository;

    public UniversidadController(UniversidadRepository universidadRepository) {
        this.universidadRepository = universidadRepository;
    }

    @PostMapping
    public Universidad createUniversidad(@RequestBody Universidad universidad) {
        return universidadRepository.save(universidad);
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @PutMapping("/{id}")
    public Universidad updateUniversidad(@PathVariable Long id, @RequestBody Universidad universidadDetails) {
        Universidad universidad = universidadRepository.findById(id).orElse(null);
        if (universidad != null) {
            universidad.setNombre(universidadDetails.getNombre());
            return universidadRepository.save(universidad);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @DeleteMapping("/{id}")
    public void deleteUniversidad(@PathVariable Long id) {
        universidadRepository.deleteById(id);
    }
}


    // @GetMapping
    // public List<Universidad> getAllUniversidades() {
    //     return universidadRepository.findAll();
    // }

    // @GetMapping("/{id}")
    // public Universidad getUniversidadById(@PathVariable Long id) {
    //     return universidadRepository.findById(id).orElse(null);
    // }