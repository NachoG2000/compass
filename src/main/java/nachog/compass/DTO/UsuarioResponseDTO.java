package nachog.compass.DTO;

public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String fechaNacimiento;
    private String genero;

    // Constructors
    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Long idUsuario, String nombreUsuario, String email, String fechaNacimiento, String genero) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    // Getters and Setters
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
}
