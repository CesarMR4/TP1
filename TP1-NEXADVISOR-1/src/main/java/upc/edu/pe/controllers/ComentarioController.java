package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void registrar(@RequestBody Comentario comentario) {
        comentarioService.insert(comentario);
    }

   
    @GetMapping("/foro/{foroId}")
    public List<Comentario> listarPorForo(@PathParam("foroId") int foroId) {
        return comentarioService.listByForoId(foroId);
    }

  
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        comentarioService.delete(id);
    }

     @PutMapping("/{id}")
    public void actualizar(@PathVariable("id") int id, @RequestBody Comentario comentario) {
        comentario.setId(id); 
        comentarioService.update(comentario);
    }
}