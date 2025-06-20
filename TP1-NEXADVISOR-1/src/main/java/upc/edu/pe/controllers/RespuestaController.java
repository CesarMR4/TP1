package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void registrar(@RequestBody Respuesta r){
        fService.insert(r);
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
