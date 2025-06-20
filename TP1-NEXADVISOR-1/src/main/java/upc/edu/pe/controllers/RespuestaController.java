package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.Respuesta;
import upc.edu.pe.serviceinterface.RespuestaService;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

	@Autowired
	private RespuestaService fService;
	
	@PostMapping
	public ResponseEntity<String> registrar(@RequestBody Respuesta r){
	    try {
	        fService.insert(r);
	        return ResponseEntity.ok("Respuesta registrada");
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Error: " + e.getMessage());
	    }
	}
	@GetMapping("/comentario/{id}")
	public List<Respuesta> listarPorComentario(@PathVariable("id") int idComentario) {
	    return fService.listarPorComentario(idComentario);
	}
	
	 @GetMapping
	    public List<Respuesta> listar(){
	        return fService.list();
	    }
	 @PutMapping
	 public void actualizar(@RequestBody Respuesta respuesta, @RequestHeader("X-User-Id") int estudianteId) {
	     Respuesta respuestaExistente = fService.findById(respuesta.getId());
	     if (respuestaExistente == null) {
	         throw new RuntimeException("Respuesta no encontrada");
	     }
	     if (respuestaExistente.getEstudiante().getId() != estudianteId) {
	         throw new RuntimeException("No puedes editar esta respuesta");
	     }
	     fService.update(respuesta);
	 }
}
