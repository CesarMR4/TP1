package upc.edu.pe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import upc.edu.pe.entities.Auxiliar;
import upc.edu.pe.serviceinterface.AsesorService;

@RestController
@RequestMapping("/asesores")
public class AsesorController {
	
	@Autowired
	private AsesorService aService;
	
	
	  @PostMapping("/registrar")
	    public ResponseEntity<String> registrarAsesor(@RequestBody Asesor asesor) {
	        try {
	            // Insertar el asesor en la base de datos
	            aService.insert(asesor);
	            return new ResponseEntity<>("Asesor registrado exitosamente.", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error al registrar el asesor.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
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
     asesor.setId(idAsesor);
     aService.update(asesor);
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<Asesor> obtenerPorId(@PathVariable("id") Integer id) {
     return aService.listId(id)
             .map(asesor -> new ResponseEntity<>(asesor, HttpStatus.OK))
             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
 }
 @PostMapping("/login/asesor")
 	public Asesor login(@RequestBody Asesor asesor){
	 return aService.login(asesor.getEmail(), asesor.getPassword())
			 .orElseThrow(()-> new RuntimeException("Credenciales incorrectas"));
 }
	
 @GetMapping("/buscar/carrera/{carrera}")
 public List<Asesor> buscarPorCarrera(@PathVariable String carrera) {
     return aService.buscarPorCarrera(carrera);
 }

 @GetMapping("/buscar/sector/{sector}")
 public List<Asesor> buscarPorSector(@PathVariable String sector) {
     return aService.buscarPorSector(sector);
 }
 @PutMapping("/reset-password")
 public String resetPassword(@RequestBody Auxiliar request) {
     Optional<Asesor> asesorOpt = aService.findByEmail(request.getEmail());

     if (asesorOpt.isPresent()) {
         Asesor asesor = asesorOpt.get();
         if (asesor.getTelefono().equals(request.getNumeroTelefonico())) {
             aService.updatePassword(request.getEmail(), request.getNuevaPassword());
             return "Contraseña actualizada correctamente.";
         } else {
             return "Número telefónico incorrecto.";
         }
     } else {
         return "Correo no encontrado.";
     }
 }
}
		
		

	


