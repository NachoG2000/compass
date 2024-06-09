package nachog.compass.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OfertaAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_oferta_academica;
    private String tipo_oferta; // Por ejemplo, carrera de grado, posgrado, etc.

    @ManyToOne
    @JoinColumn(name = "id_universidad")
    @JsonBackReference
    private Universidad universidad;

    @OneToMany(mappedBy = "ofertaAcademica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Carrera> carreras;

    // Getters y Setters
    public Long getId_oferta_academica() {
        return id_oferta_academica;
    }

    public void setId_oferta_academica(Long id_oferta_academica) {
        this.id_oferta_academica = id_oferta_academica;
    }

    public String getTipo_oferta() {
        return tipo_oferta;
    }

    public void setTipo_oferta(String tipo_oferta) {
        this.tipo_oferta = tipo_oferta;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
}
