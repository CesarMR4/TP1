package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.Historial;
import upc.edu.pe.serviceinterface.HistorialService;

@RestController
@RequestMapping("/historial")
public class HistorialController {

	@Autowired
	private HistorialService hiService;
	
	@PostMapping
    public void registrar(@RequestBody Historial h){
        hiService.insert(h);
    }
	
	 @GetMapping
	    public List<Historial> listar(){
	        return hiService.list();
	    }
	
}
