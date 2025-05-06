package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.Asesor;

import upc.edu.pe.serviceinterface.AsesorService;

@RestController
@RequestMapping("/asesores")
public class AsesorController {
	
	@Autowired
	private AsesorService aService;
	
	
	@PostMapping
    public void registrar(@RequestBody Asesor asesor){
        aService.insert(asesor);
    }
 @GetMapping
    public List<Asesor> listar(){
        return aService.list();
    }
 @DeleteMapping("/{id}")
    public void eliminar(@PathVariable ("id") Integer idAsesor){ aService.delete(idAsesor);
    }

 @PutMapping("/{id}")
 public void actualizar(@PathVariable("id") Integer idAsesor, @RequestBody Asesor asesor) {
     asesor.setId_Asesor(idAsesor);
     aService.update(asesor);
 }
	
}
		
		

	


