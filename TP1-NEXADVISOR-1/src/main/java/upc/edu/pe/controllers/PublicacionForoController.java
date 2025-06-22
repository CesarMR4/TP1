package upc.edu.pe.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.PublicacionForo;
import upc.edu.pe.serviceinterface.PublicacionForoService;

@RestController
@RequestMapping("/publicacion")
public class PublicacionForoController {

    @Autowired
    private PublicacionForoService publicacionService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody PublicacionForo publicacion) {
      
        if (publicacion.getTitulo() == null || publicacion.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título no puede estar vacío.");
        }
        if (publicacion.getContenido() == null || publicacion.getContenido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El contenido no puede estar vacío.");
        }

 
        if (publicacion.getFechaPublicacion() == null) {
            publicacion.setFechaPublicacion(new Date());
        }

        publicacionService.insert(publicacion);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PublicacionForo>> listar() {
        return ResponseEntity.ok(publicacionService.listOrderByFechaDesc());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
        publicacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionForo> obtenerPorId(@PathVariable("id") int id) {
        Optional<PublicacionForo> publicacion = publicacionService.listById(id);
        return publicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}