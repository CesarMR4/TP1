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

import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.serviceinterface.EstudianteService;


@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
	
	@Autowired
    private EstudianteService pService;
	
	 @PostMapping
	    public void registrar(@RequestBody Estudiante estudiante){
	        pService.insert(estudiante);
	    }
	 @GetMapping
	    public List<Estudiante> listar(){
	        return pService.list();
	    }
	 @DeleteMapping("/{id}")
	    public void eliminar(@PathVariable ("id") Integer idEstudiante){ pService.delete(idEstudiante);
	    }
	 @GetMapping("/{id}")
	 public Estudiante obtenerPorId(@PathVariable("id") Integer idEstudiante) {
	     return pService.listId_Estudiante(idEstudiante)
	                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
	 }
	 @PutMapping("/{id}")
	 public void actualizar(@PathVariable("id") Integer idEstudiante, @RequestBody Estudiante estudiante) {  
	     estudiante.setIdEstudiante(idEstudiante);
	       pService.update(estudiante);
	    }
	 @PostMapping("/login")
	 public Estudiante login(@RequestBody Estudiante estudiante) {
	     return pService.login(estudiante.getEmail(), estudiante.getPassword())
	                    .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
	 }
	   

}
