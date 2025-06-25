package upc.edu.pe.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import upc.edu.pe.entities.Comentario;
import upc.edu.pe.serviceinterface.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

  
    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody Comentario comentario) {
        try {
            if (comentario.getFechacreacion() == null) {
                comentario.setFechacreacion(new Date());
            }

            if (comentario.getEstudiante() == null || comentario.getEstudiante().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estudiante inválido");
            }

            if (comentario.getAsesor() == null || comentario.getAsesor().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Asesor inválido");
            }

            comentarioService.insert(comentario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comentario registrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar comentario");
        }
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int id, @RequestHeader("X-User-Id") int estudianteId) {
        Comentario comentarioExistente = comentarioService.findById(id);
        if (comentarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado");
        }
        if (comentarioExistente.getEstudiante().getId() != estudianteId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes eliminar este comentario");
        }
        comentarioService.delete(id);
        return ResponseEntity.ok().body("Comentario eliminado correctamente");
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable("id") int id, @RequestBody Comentario comentario, @RequestHeader("X-User-Id") int estudianteId) {
        Comentario comentarioExistente = comentarioService.findById(id);
        if (comentarioExistente == null) {
            throw new RuntimeException("Comentario no encontrado");
        }
        if (comentarioExistente.getEstudiante().getId() != estudianteId) {
            throw new RuntimeException("No puedes editar este comentario");
        }
        comentario.setId(id);
        comentarioService.update(comentario);
    }
    @GetMapping("/asesor/{id}")
    public List<Comentario> listarComentariosPorAsesor(@PathVariable("id") int idAsesor) {
        return comentarioService.listarPorAsesor(idAsesor);
    }

}