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

import nachog.compass.entity.OfertaAcademica;
import nachog.compass.entity.Universidad;
import nachog.compass.repository.OfertaAcademicaRepository;
import nachog.compass.repository.UniversidadRepository;

@RestController
@RequestMapping("/ofertas")
public class OfertaAcademicaController {

    private OfertaAcademicaRepository ofertaAcademicaRepository;
    private UniversidadRepository universidadRepository;

    public OfertaAcademicaController(OfertaAcademicaRepository ofertaAcademicaRepository, UniversidadRepository universidadRepository) {
        this.ofertaAcademicaRepository = ofertaAcademicaRepository;
        this.universidadRepository = universidadRepository;
    }

    @GetMapping
    public List<OfertaAcademica> getAllOfertas() {
        return ofertaAcademicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public OfertaAcademica getOfertaById(@PathVariable Long id) {
        return ofertaAcademicaRepository.findById(id).orElse(null);
    }

    @PostMapping("/universidad/{universidadId}")
    public OfertaAcademica createOferta(@PathVariable Long universidadId, @RequestBody OfertaAcademica ofertaAcademica) {
        Universidad universidad = universidadRepository.findById(universidadId).orElse(null);
        if (universidad != null) {
            ofertaAcademica.setUniversidad(universidad);
            return ofertaAcademicaRepository.save(ofertaAcademica);
        }
        return null;
    }

    @PutMapping("/{id}")
    public OfertaAcademica updateOferta(@PathVariable Long id, @RequestBody OfertaAcademica ofertaDetails) {
        OfertaAcademica ofertaAcademica = ofertaAcademicaRepository.findById(id).orElse(null);
        if (ofertaAcademica != null) {
            ofertaAcademica.setTipo_oferta(ofertaDetails.getTipo_oferta());
            return ofertaAcademicaRepository.save(ofertaAcademica);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOferta(@PathVariable Long id) {
        ofertaAcademicaRepository.deleteById(id);
    }
}