package nachog.compass.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import nachog.compass.entity.Carrera;
import nachog.compass.repository.CarreraRepository;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Objects;

@Service
public class LLMService {

    private final ChatClient chatClient;
    private final CarreraRepository carreraRepository;

    public LLMService(ChatClient chatClient, CarreraRepository carreraRepository) {
        this.chatClient = chatClient;
        this.carreraRepository = carreraRepository;
    }

    public List<Carrera> generateCarrerasList(String respuestasFormatted, String carreras) {
        String message = """
            A partir del siguiente JSON de la solicitud del usuario, donde se incluye su nombre, intereses y aptitudes (donde el valor 1 significa completamente en desacuerdo y 5 significa completamente de acuerdo): {respuestasFormatted}
            Piensa paso a paso y genera un modelo mental de la personalidad del usuario en base a sus intereses y aptitudes. Considera los aspectos psicológicos que podrían influir en la elección de una carrera, como la motivación, los valores, los rasgos de personalidad, etc.

            Luego, selecciona una lista de las 5 carreras que mejor se ajusten a este modelo de personalidad del usuario

            Carreras disponibles:
            {carreras}

            IMPORTANTE: No incluyas texto anterior ni posterior a esta lista SOLO LA LISTA DE ID DE CARRERAS. Las carreras deben estar separadas por un salto de linea.
            """;

        PromptTemplate promptTemplate = new PromptTemplate(message);
        Prompt prompt = promptTemplate.create(Map.of("respuestasFormatted", respuestasFormatted, "carreras", carreras));

        String response = chatClient.call(prompt).getResult().getOutput().getContent();

        // VER BIEN PQ ESTA HECHO CON CHATGPT

        // Limpiar y convertir la respuesta en una lista de IDs de carreras
        List<Long> carreraIds = Arrays.stream(response.split("\n"))
                .map(String::trim) // Eliminar espacios en blanco y saltos de línea
                .filter(line -> !line.isEmpty()) // Filtrar líneas vacías
                .map(line -> {
                    try {
                        return Long.parseLong(line);
                    } catch (NumberFormatException e) {
                        // Manejo de error si la conversión falla
                        System.err.println("Error al convertir a Long: " + line);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filtrar nulos
                .collect(Collectors.toList());

        // Convertir los IDs de carreras a objetos Carrera
        return carreraRepository.findAllById(carreraIds);
    }
}