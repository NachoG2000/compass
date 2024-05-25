package nachog.compass.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class CarreraSeleccionada {

    @EmbeddedId
    private CarreraSeleccionadaKey id;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // Getters and Setters
    public CarreraSeleccionadaKey getId() {
        return id;
    }

    public void setId(CarreraSeleccionadaKey id) {
        this.id = id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
