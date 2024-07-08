package nachog.compass.service;

import nachog.compass.entity.Administrador;
import nachog.compass.repository.AdministradorRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrador administrador = administradorRepository.findByEmailAdmin(email)
            .orElseThrow(() -> new UsernameNotFoundException("Administrador not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
            administrador.getEmailAdmin(),
            administrador.getContrasenaAdmin(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     private final AdministradorRepository administradorRepository;

//     public CustomUserDetailsService(@Lazy AdministradorRepository administradorRepository) {
//         this.administradorRepository = administradorRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Administrador administrador = administradorRepository.findByEmailAdmin(email);
//         if (administrador == null) {
//             throw new UsernameNotFoundException("Administrador not found with email: " + email);
//         }
//         return new org.springframework.security.core.userdetails.User(
//                 administrador.getEmailAdmin(),
//                 administrador.getContrasenaAdmin(),
//                 Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
//         );
//     }
// }
