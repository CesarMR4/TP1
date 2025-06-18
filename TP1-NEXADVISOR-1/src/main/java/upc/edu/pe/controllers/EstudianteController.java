package upc.edu.pe.controllers;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.entities.Auxiliar;
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
	 @PostMapping("/login/estudiante")
	 public Estudiante login(@RequestBody Estudiante estudiante) {
	     return pService.login(estudiante.getEmail(), estudiante.getPassword())
	                    .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
	 }
	 @PutMapping("/reset-password")
	 public String resetPassword(@RequestBody Auxiliar request) {
	     Optional<Estudiante> estudianteOpt = pService.findByEmail(request.getEmail());

	     if (estudianteOpt.isPresent()) {
	         Estudiante estudiante = estudianteOpt.get();
	         if (estudiante.getTelefono().equals(request.getNumeroTelefonico())) {
	             pService.updatePassword(request.getEmail(), request.getNuevaPassword());
	             return "Contraseña actualizada correctamente.";
	         } else {
	             return "Número telefónico incorrecto.";
	         }
	     } else {
	         return "Correo no encontrado.";
	     }
	 }
}
/*
@PostMapping("/registro-con-cv")
public ResponseEntity<String> registrarConCurriculum(
    @RequestParam("nombre") String nombre,
    @RequestParam("email") String email,
    @RequestParam("contrasena") String contrasena,
    @RequestParam("direccion") String direccion,
    @RequestParam("telefono") String telefono,
    @RequestParam("carrera") String carrera,
    @RequestParam("curriculum") MultipartFile file
) {
    try {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setEmail(email);
        estudiante.setPassword(contrasena);
        estudiante.setDireccion(direccion);
        estudiante.setTelefono(telefono);
        estudiante.setCarrera(carrera);
        estudiante.setFecha_Registro(new Date());
        estudiante.setRol("estudiante");
        estudiante.setCurriculum(file.getBytes()); // importante

        pService.insert(estudiante);

        return ResponseEntity.ok("Estudiante registrado correctamente.");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el archivo.");
    }
}
   

}
*/