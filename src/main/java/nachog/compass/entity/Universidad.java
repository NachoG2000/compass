package nachog.compass.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversidad;
    
    private String nombre;

    @OneToMany(mappedBy = "universidadSeleccionada", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "universidad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OfertaAcademica> ofertasAcademicas;

    @OneToOne(mappedBy = "universidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Formulario formulario;

    @OneToOne(mappedBy = "universidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Administrador administrador;

    // Getters y Setters
    public Long getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Long idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OfertaAcademica> getOfertasAcademicas() {
        return ofertasAcademicas;
    }

    public void setOfertasAcademicas(List<OfertaAcademica> ofertasAcademicas) {
        this.ofertasAcademicas = ofertasAcademicas;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
