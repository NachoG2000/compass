package nachog.compass.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class CarreraSeleccionadaKey implements Serializable {

    @Column(name = "id_carrera")
    private Long idCarrera;

    @Column(name = "id_usuario")
    private Long idUsuario;

    // Getters, Setters, hashCode, equals
    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarreraSeleccionadaKey that = (CarreraSeleccionadaKey) o;

        if (!idCarrera.equals(that.idCarrera)) return false;
        return idUsuario.equals(that.idUsuario);
    }

    @Override
    public int hashCode() {
        int result = idCarrera.hashCode();
        result = 31 * result + idUsuario.hashCode();
        return result;
    }
}
