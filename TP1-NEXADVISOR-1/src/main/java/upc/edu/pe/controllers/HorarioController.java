package upc.edu.pe.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import upc.edu.pe.entities.Horario;
import upc.edu.pe.serviceinterface.HorarioService;




@RestController
@RequestMapping("/horario")
public class HorarioController {
	
	@Autowired
    private HorarioService pService;
	
	@PostMapping
    public void registrar(@RequestBody Horario h){
        pService.insert(h);
    }
	
	 @GetMapping
	    public List<Horario> listar(){
	        return pService.list();
	    }
	 
	 @DeleteMapping("/{id}")
	    public void eliminar(@PathVariable ("id") Integer id){ pService.delete(id);}
	 
	 @PutMapping
	    public void actualizar(@RequestBody Horario u){ pService.insert(u);}

}
