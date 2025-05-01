package org.springframework.stereotype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.RestController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.serviceinterface.EstudianteService;


@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
	
	@Autowired
    private EstudianteService pService;
	
	 @PostMapping
	    public void registrar(@RequestBody Estudiante tp){
	        pService.insert(tp);
	    }
	 @GetMapping
	    public List<Estudiante> listar(){
	        return pService.list();// devuelve JSON para hacer las validaciones en PostMan o Swagger
	    }
	 @DeleteMapping("/{id}")
	    public void eliminar(@PathVariable ("id") Integer idEstudiante){ pService.delete(idEstudiante);
	    }
	 

}
