package nachog.compass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import nachog.compass.DTO.PreguntaRequestDTO;
import nachog.compass.DTO.RequestObject;
import nachog.compass.entity.Carrera;
import nachog.compass.entity.Formulario;
import nachog.compass.entity.Pregunta;
import nachog.compass.service.FormularioService;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class FormularioController {

    private final FormularioService formularioService;

    public FormularioController(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    @PostMapping("/createFormulario")
    public Formulario createFormulario(@RequestBody Formulario formulario) {
    return formularioService.createFormulario(formulario);
    }

    @PostMapping("/createPregunta")
    public ResponseEntity<Pregunta> createPregunta(@RequestBody PreguntaRequestDTO preguntaRequestDTO) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createPregunta/{universidadId}")
    public ResponseEntity<Pregunta> createPreguntaPorUniversidad(@RequestBody Pregunta pregunta, @PathVariable Long universidadId) {
        Pregunta createdPregunta = formularioService.createPreguntaPorUniversidad(pregunta, universidadId);
        return ResponseEntity.ok(createdPregunta);
    }
    
    @GetMapping("/preguntas/{universidadId}")
    public ResponseEntity<List<Pregunta>> getPreguntasPorUniversidad(@PathVariable Long universidadId) {
        List<Pregunta> preguntas = formularioService.getPreguntasPorUniversidadId(universidadId);
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/formulario")
    public ResponseEntity<List<Carrera>> handleFormularioRequest(@RequestBody RequestObject request) {
        // Manejar la solicitud del formulario utilizando el servicio
        System.out.println("Received request: " + request);

        List<Carrera> response = formularioService.processFormularioRequest(request);

        System.out.println("Response to be sent: " + response);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletePregunta/{id}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long id) {
        formularioService.deletePregunta(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatePregunta/{id}")
    public ResponseEntity<Pregunta> updatePregunta(@PathVariable Long id, @RequestBody Pregunta preguntaDetails) {
        return formularioService.updatePregunta(id, preguntaDetails);
    }
}
