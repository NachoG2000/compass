package nachog.compass.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreguntaRequestDTO {
    private Long id_pregunta;
    
    @JsonProperty("texto_pregunta")
    private String texto_pregunta;

    // Constructor
    public PreguntaRequestDTO() {
        // Default constructor
    }

    public PreguntaRequestDTO(Long id_pregunta, String texto_pregunta) {
        this.id_pregunta = id_pregunta;
        this.texto_pregunta = texto_pregunta;
    }

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
}
