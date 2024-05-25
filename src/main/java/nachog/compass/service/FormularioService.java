package nachog.compass.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nachog.compass.DTO.RequestObject;
import nachog.compass.DTO.RequestObject.UserData;
import nachog.compass.entity.Carrera;
import nachog.compass.entity.Formulario;
import nachog.compass.entity.Pregunta;
import nachog.compass.entity.Respuesta;
import nachog.compass.entity.Usuario;
import nachog.compass.repository.FormularioRepository;
import nachog.compass.repository.PreguntaRepository;
import nachog.compass.repository.RespuestaRepository;

@Service
public class FormularioService {

    private final UsuarioService usuarioService;
    private final RespuestaRepository respuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final FormularioRepository formularioRepository;
    private final LLMService llmService;
    private final CarreraService carreraService;

    public FormularioService(UsuarioService usuarioService,
                             RespuestaRepository respuestaRepository,
                             PreguntaRepository preguntaRepository,
                             FormularioRepository formularioRepository,
                             LLMService llmService,
                             CarreraService carreraService) {
        this.usuarioService = usuarioService;
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.formularioRepository = formularioRepository;
        this.llmService = llmService;
        this.carreraService = carreraService;
    }

    public List<Carrera> processFormularioRequest(RequestObject request) {
        Usuario usuario = mapToUsuario(request.getUserData());
        usuarioService.saveUsuario(usuario);

        String respuestasFormatted = saveRespuestasAndGetFormattedString(request.getSurveyData(), usuario);

        Long idOfertaAcademica = 1L;  // CAMBIAR ESTO CUANDO PUEDA PASAR EL ID DE OFERTA ACADEMICA DESDE EL FRONTEND
        String carreras = carreraService.getCarrerasDisponiblesString(idOfertaAcademica);

        List<Carrera> carrerasSeleccionadas = llmService.generateCarrerasList(respuestasFormatted, carreras);

        carreraService.saveCarrerasSeleccionadas(usuario, carrerasSeleccionadas);

        return carrerasSeleccionadas;
    }

    private Usuario mapToUsuario(UserData userData) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(userData.getName());
        usuario.setEmail(userData.getEmail());
        usuario.setFechaNacimiento(userData.getBirthday());
        usuario.setGenero(userData.getGender());
        usuario.setModalidadDeseada(userData.getModality());
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

    public Pregunta createPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    public List<Pregunta> getPreguntas(Long formularioId) {
        return preguntaRepository.findByFormularioId(formularioId);
    }
}