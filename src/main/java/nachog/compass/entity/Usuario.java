package nachog.compass.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    private String nombreUsuario;
    private String email;
    private String fechaNacimiento;
    private String genero;
    
    @ManyToOne
    @JoinColumn(name = "id_universidad_seleccionada")
    @JsonBackReference("universidad-usuario")
    private Universidad universidadSeleccionada;

    @ManyToOne
    @JoinColumn(name = "id_oferta_seleccionada")
    @JsonBackReference("oferta-usuario")
    private OfertaAcademica ofertaSeleccionada;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    public Usuario() {
    }

    // Getters y setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;    
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Universidad getUniversidadSeleccionada() {
        return universidadSeleccionada;
    }

    public void setUniversidadSeleccionada(Universidad universidadSeleccionada) {
        this.universidadSeleccionada = universidadSeleccionada;
    }

    public OfertaAcademica getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(OfertaAcademica ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

}
