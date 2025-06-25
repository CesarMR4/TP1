package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.serviceinterface.PuntuacionService;

@RestController
@RequestMapping("/puntuaciones")
public class PuntuacionController {
    
    @Autowired
    private PuntuacionService pService;
    
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Puntuacion puntuacion) {
        if (puntuacion.getPuntuacion() < 1 || puntuacion.getPuntuacion() > 5) {
            return new ResponseEntity<>("La puntuación debe estar entre 1 y 5", HttpStatus.BAD_REQUEST);
        }
        pService.insert(puntuacion);
        return new ResponseEntity<>("Puntuación registrada correctamente", HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Puntuacion> listar() {
        return pService.list();
    }
    
    @GetMapping("/asesor/{idAsesor}")
    public List<Puntuacion> listarPorAsesor(@PathVariable int idAsesor) {
        return pService.listarPorAsesor(idAsesor);
    }
    
    @GetMapping("/promedio/{idAsesor}")
    public Double promedioPorAsesor(@PathVariable int idAsesor) {
        Double promedio = pService.obtenerPromedioPorAsesor(idAsesor);
        return promedio != null ? promedio : 0.0;
    }
    
    
}