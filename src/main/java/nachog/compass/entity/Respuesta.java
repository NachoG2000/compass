package nachog.compass.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private Integer valorRespuesta;

    private Long idPregunta;

    public Respuesta() {
    }

    // Getters y setters
    public Long getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Long idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getValorRespuesta() {
        return valorRespuesta;
    }

    public void setValorRespuesta(Integer valorRespuesta) {
        this.valorRespuesta = valorRespuesta;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }
}