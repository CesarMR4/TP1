package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.Foro;
import upc.edu.pe.serviceinterface.ForoService;

@RestController
@RequestMapping("/foro")
public class ForoController {

	@Autowired
	private ForoService fService;
	
	@PostMapping
    public void registrar(@RequestBody Foro f){
        fService.insert(f);
    }
	
	 @GetMapping
	    public List<Foro> listar(){
	        return fService.list();
	    }
	 @PutMapping
	 public void actualizar(@RequestBody Foro foro) {
	     fService.update(foro);
	 }
}
