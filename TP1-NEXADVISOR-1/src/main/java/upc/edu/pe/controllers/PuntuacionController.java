package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upc.edu.pe.entities.Asesor;
import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.serviceinterface.PuntuacionService;

@RestController
@RequestMapping("/puntuaciones")
public class PuntuacionController {
    
    @Autowired
    private PuntuacionService pService;
    
    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody Puntuacion puntuacion) {
        try {
            Asesor asesor = new Asesor();
            asesor.setId(puntuacion.getIdAsesor());
            puntuacion.setAsesor(asesor);
            pService.insert(puntuacion);
            return ResponseEntity.ok().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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