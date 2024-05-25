package nachog.compass.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrera;
    private String nombre_carrera;
    private String descripcion_carrera;
    private String duracion_carrera;
    private String link_web_carrera;

    @ManyToOne
    @JoinColumn(name = "id_oferta_academica")
    @JsonBackReference
    private OfertaAcademica ofertaAcademica;

    // Getters y Setters
    public Long getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Long id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public String getDescripcion_carrera() {
        return descripcion_carrera;
    }

    public void setDescripcion_carrera(String descripcion_carrera) {
        this.descripcion_carrera = descripcion_carrera;
    }

    public String getDuracion_carrera() {
        return duracion_carrera;
    }

    public void setDuracion_carrera(String duracion_carrera) {
        this.duracion_carrera = duracion_carrera;
    }

    public String getLink_web_carrera() {
        return link_web_carrera;
    }

    public void setLink_web_carrera(String link_web_carrera) {
        this.link_web_carrera = link_web_carrera;
    }

    public OfertaAcademica getOfertaAcademica() {
        return ofertaAcademica;
    }

    public void setOfertaAcademica(OfertaAcademica ofertaAcademica) {
        this.ofertaAcademica = ofertaAcademica;
    }

    public Long getId() {
        return id_carrera;
    }
}