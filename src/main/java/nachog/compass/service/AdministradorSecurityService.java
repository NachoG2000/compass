package nachog.compass.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nachog.compass.entity.Administrador;
import nachog.compass.repository.AdministradorRepository;

@Service
public class AdministradorSecurityService {

    private AdministradorRepository administradorRepository;

    public AdministradorSecurityService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public boolean hasAccessToUniversidad(Authentication authentication, Long universidadId) {
        String email = authentication.getName();
        Administrador administrador = administradorRepository.findByEmailAdmin(email)
            .orElseThrow(() -> new UsernameNotFoundException("Administrador not found"));
        return administrador.getUniversidad().getIdUniversidad().equals(universidadId);
    }
}
