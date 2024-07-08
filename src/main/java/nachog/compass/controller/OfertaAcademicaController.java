package nachog.compass.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nachog.compass.DTO.OfertaAcademicaResponseDTO;
import nachog.compass.entity.OfertaAcademica;
import nachog.compass.entity.Universidad;
import nachog.compass.repository.OfertaAcademicaRepository;
import nachog.compass.repository.UniversidadRepository;

@RestController
@RequestMapping("/ofertas")
@CrossOrigin(origins = "http://localhost:5173")
public class OfertaAcademicaController {

    private OfertaAcademicaRepository ofertaAcademicaRepository;
    private UniversidadRepository universidadRepository;

    public OfertaAcademicaController(OfertaAcademicaRepository ofertaAcademicaRepository, UniversidadRepository universidadRepository) {
        this.ofertaAcademicaRepository = ofertaAcademicaRepository;
        this.universidadRepository = universidadRepository;
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @PutMapping("/{id}")
    public OfertaAcademica updateOferta(@PathVariable Long id, @RequestBody OfertaAcademica ofertaDetails) {
        OfertaAcademica ofertaAcademica = ofertaAcademicaRepository.findById(id).orElse(null);
        if (ofertaAcademica != null) {
            ofertaAcademica.setTipo_oferta(ofertaDetails.getTipo_oferta());
            return ofertaAcademicaRepository.save(ofertaAcademica);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @DeleteMapping("/{id}")
    public void deleteOferta(@PathVariable Long id) {
        ofertaAcademicaRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') and @administradorSecurityService.hasAccessToUniversidad(authentication, #id)")
    @PostMapping("/create/universidad/{universidadId}")
    public OfertaAcademica createOfertaPorUniversidadId(@PathVariable Long universidadId, @RequestBody OfertaAcademica ofertaAcademica) {
        Universidad universidad = universidadRepository.findById(universidadId).orElse(null);
        if (universidad != null) {
            ofertaAcademica.setUniversidad(universidad);
            return ofertaAcademicaRepository.save(ofertaAcademica);
        }
        return null;
    }

    @GetMapping("/universidad/{universidadId}")
    public List<OfertaAcademicaResponseDTO> getOfertasByUniversidadId(@PathVariable Long universidadId) {
        Universidad universidad = universidadRepository.findById(universidadId).orElse(null);
        if (universidad != null) {
            return ofertaAcademicaRepository.findByUniversidadIdUniversidad(universidadId).stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private OfertaAcademicaResponseDTO convertToDto(OfertaAcademica ofertaAcademica) {
        return new OfertaAcademicaResponseDTO(ofertaAcademica.getId_oferta_academica(), ofertaAcademica.getTipo_oferta());
    }
}

    // @GetMapping
    // public List<OfertaAcademica> getAllOfertas() {
    //     return ofertaAcademicaRepository.findAll();
    // }

    // @GetMapping("/{id}")
    // public OfertaAcademica getOfertaById(@PathVariable Long id) {
    //     return ofertaAcademicaRepository.findById(id).orElse(null);
    // }