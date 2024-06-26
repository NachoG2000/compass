package nachog.compass.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario;
    private String nombre_formulario;

    @OneToOne
    @JoinColumn(name = "id_universidad")
    private Universidad universidad;

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pregunta> preguntas;

    // Getters y Setters
    public Long getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(Long id_formulario) {
        this.id_formulario = id_formulario;
    }

    public String getNombre_formulario() {
        return nombre_formulario;
    }

    public void setNombre_formulario(String nombre_formulario) {
        this.nombre_formulario = nombre_formulario;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}