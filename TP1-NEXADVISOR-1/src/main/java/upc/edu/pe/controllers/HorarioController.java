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
	public void registrar(@RequestBody Horario h) {
	    System.out.println("Recibido horario para asesor con ID: " + h.getAsesor().getId());
	    pService.insert(h);
	}


    @GetMapping
    public List<Horario> listar() {
        return pService.list();
    }


    @GetMapping("/{id}")
    public Optional<Horario> obtenerPorId(@PathVariable("id") int id) {
        return pService.ListarId(id);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        pService.delete(id);
    }


    @PutMapping("/{id}")
    public void actualizar(@PathVariable("id") int id, @RequestBody Horario horario) {
        Optional<Horario> existingHorario = pService.ListarId(id);
        if (existingHorario.isPresent()) {
            Horario updatedHorario = existingHorario.get();
            updatedHorario.setDia(horario.getDia());
            updatedHorario.setHoraInicio(horario.getHoraInicio());
            updatedHorario.setHoraFin(horario.getHoraFin());
            pService.insert(updatedHorario);  
        }
    }
    
    @GetMapping("/asesor/{idAsesor}")
    public List<Horario> obtenerPorAsesor(@PathVariable("idAsesor") int idAsesor) {
        return pService.findByAsesorId(idAsesor);
    }
    


}
