package nachog.compass.DTO;

public class OfertaAcademicaResponseDTO {
    private Long idOfertaAcademica;
    private String tipoOferta;

    // Constructor
    public OfertaAcademicaResponseDTO(Long idOfertaAcademica, String tipoOferta) {
        this.idOfertaAcademica = idOfertaAcademica;
        this.tipoOferta = tipoOferta;
    }

    // Getters y Setters
    public Long getIdOfertaAcademica() {
        return idOfertaAcademica;
    }

    public void setIdOfertaAcademica(Long idOfertaAcademica) {
        this.idOfertaAcademica = idOfertaAcademica;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }
}