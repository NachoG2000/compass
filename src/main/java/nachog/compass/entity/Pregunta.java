package nachog.compass.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pregunta;
    private String texto_pregunta;

    @ManyToOne
    @JoinColumn(name = "id_formulario")
    @JsonBackReference
    private Formulario formulario;

    // Getters y Setters
    public Long getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(Long id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public String getTexto_pregunta() {
        return texto_pregunta;
    }

    public void setTexto_pregunta(String texto_pregunta) {
        this.texto_pregunta = texto_pregunta;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
}