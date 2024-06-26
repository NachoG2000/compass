package nachog.compass.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nachog.compass.DTO.RequestObject;
import nachog.compass.DTO.RequestObject.UserData;
import nachog.compass.entity.Carrera;
import nachog.compass.entity.Formulario;
import nachog.compass.entity.Pregunta;
import nachog.compass.entity.Respuesta;
import nachog.compass.entity.Usuario;
import nachog.compass.repository.FormularioRepository;
import nachog.compass.repository.OfertaAcademicaRepository;
import nachog.compass.repository.PreguntaRepository;
import nachog.compass.repository.RespuestaRepository;
import nachog.compass.repository.UniversidadRepository;

@Service
public class FormularioService {

    private final UsuarioService usuarioService;
    private final RespuestaRepository respuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final FormularioRepository formularioRepository;
    private final OfertaAcademicaRepository ofertaAcademicaRepository;
    private final UniversidadRepository universidadRepository;
    private final LLMService llmService;
    private final CarreraService carreraService;

    public FormularioService(UsuarioService usuarioService,
                             RespuestaRepository respuestaRepository,
                             PreguntaRepository preguntaRepository,
                             FormularioRepository formularioRepository,
                             LLMService llmService,
                             CarreraService carreraService,
                             OfertaAcademicaRepository ofertaAcademicaRepository,
                             UniversidadRepository universidadRepository) {
        this.usuarioService = usuarioService;
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.formularioRepository = formularioRepository;
        this.llmService = llmService;
        this.carreraService = carreraService;
        this.ofertaAcademicaRepository = ofertaAcademicaRepository;
        this.universidadRepository = universidadRepository;
    }

    public List<Carrera> processFormularioRequest(RequestObject request) {
        Usuario usuario = mapToUsuario(request.getUserData());
        usuarioService.saveUsuario(usuario);

        String respuestasFormatted = saveRespuestasAndGetFormattedString(request.getSurveyData(), usuario);

        String carreras = 
        carreraService.getCarrerasDisponiblesString(usuario.getOfertaSeleccionada().getId_oferta_academica());

        List<Carrera> carrerasSeleccionadas = llmService.generateCarrerasList(respuestasFormatted, carreras);

        carreraService.saveCarrerasSeleccionadas(usuario, carrerasSeleccionadas);

        return carrerasSeleccionadas;
    }

    private Usuario mapToUsuario(UserData userData) { 
        System.out.println(userData.toString());
        
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(userData.getName());
        usuario.setEmail(userData.getEmail());
        usuario.setFechaNacimiento(userData.getBirthday());
        usuario.setGenero(userData.getGender());
        usuario.setOfertaSeleccionada(ofertaAcademicaRepository.findById(userData.getOfertaAcademicaId()).orElseThrow());
        usuario.setUniversidadSeleccionada(universidadRepository.findById(userData.getUniversidadId()).orElseThrow());

        return usuario;
    }

    private String saveRespuestasAndGetFormattedString(Map<Long, Integer> surveyData, Usuario usuario) {
        StringBuilder respuestasStringBuilder = new StringBuilder();

        for (Map.Entry<Long, Integer> entry : surveyData.entrySet()) {
            Respuesta respuesta = new Respuesta();
            respuesta.setUsuario(usuario);
            respuesta.setValorRespuesta(entry.getValue());
            respuesta.setIdPregunta(entry.getKey());

            respuestaRepository.save(respuesta);

            Optional<Pregunta> preguntaOptional = preguntaRepository.findById(entry.getKey());
            if (preguntaOptional.isPresent()) {
                Pregunta pregunta = preguntaOptional.get();
                respuestasStringBuilder.append(pregunta.getTexto_pregunta())
                                       .append(": ")
                                       .append(entry.getValue())
                                       .append("\n");
            } else {
                respuestasStringBuilder.append("Pregunta ID ")
                                       .append(entry.getKey())
                                       .append(" no encontrada")
                                       .append(": ")
                                       .append(entry.getValue())
                                       .append("\n");
            }
        }

        return respuestasStringBuilder.toString();
    }

    public Formulario createFormulario(Formulario formulario) {
        return formularioRepository.save(formulario);
    }

    public Pregunta createPreguntaPorUniversidad(Pregunta pregunta, Long universidadId){
        Long formularioId = obtenerFormularioIdPorUniversidadId(universidadId);
        pregunta.setFormulario(formularioRepository.findById(formularioId).orElse(null));
        return preguntaRepository.save(pregunta);
    }
    
    public List<Pregunta> getPreguntas(Long formularioId) {
        return preguntaRepository.findByFormularioId(formularioId);
    }

    public List<Pregunta> getPreguntasPorUniversidadId(Long universidadId) {
        Long formularioId = obtenerFormularioIdPorUniversidadId(universidadId);
        return getPreguntas(formularioId);
    }

    private Long obtenerFormularioIdPorUniversidadId(Long universidadId) {
        Formulario formulario = formularioRepository.findByUniversidadIdUniversidad(universidadId);
        return formulario.getId_formulario();
    }

    public void deletePregunta(Long id) {
        preguntaRepository.deleteById(id);
    }

    public ResponseEntity<Pregunta> updatePregunta(Long id, Pregunta preguntaDetails) {
        Pregunta pregunta = preguntaRepository.findById(id).orElse(null);
        if (pregunta != null) {
            pregunta.setTexto_pregunta(preguntaDetails.getTexto_pregunta());
            Pregunta updatedPregunta = preguntaRepository.save(pregunta);
            return ResponseEntity.ok(updatedPregunta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}