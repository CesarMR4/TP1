package upc.edu.pe.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.PublicacionForo;
import upc.edu.pe.entities.RespuestaForo;
import upc.edu.pe.serviceinterface.RespuestaForoService;

@RestController
@RequestMapping("/respuesta-foro")
public class RespuestaForoController {

    @Autowired
    private RespuestaForoService respuestaService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RespuestaForo respuesta) {
        if (respuesta.getContenido() == null || respuesta.getContenido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El contenido no puede estar vacío.");
        }

        if (respuesta.getFechaRespuesta() == null) {
            respuesta.setFechaRespuesta(new Date());
        }

        respuestaService.insert(respuesta);
        return ResponseEntity.status(201).build();
    }
    
    @GetMapping
    public ResponseEntity<List<RespuestaForo>> listar() {
        return ResponseEntity.ok(respuestaService.list());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        respuestaService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaForo> obtenerPorId(@PathVariable("id") int id) {
        Optional<RespuestaForo> respuesta = respuestaService.listById(id);
        return respuesta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/por-publicacion/{idPublicacion}")
    public ResponseEntity<List<RespuestaForo>> listarPorPublicacion(@PathVariable("idPublicacion") int idPublicacion) {
        PublicacionForo publicacion = new PublicacionForo();
        publicacion.setId(idPublicacion);

        List<RespuestaForo> respuestas = respuestaService.findByPublicacionOrderByFechaDesc(publicacion);
        return ResponseEntity.ok(respuestas);
    }
}