package upc.edu.pe.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.entities.Auxiliar;
import upc.edu.pe.serviceinterface.EstudianteService;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService pService;
    
    @PostMapping
    public ResponseEntity<Map<String, String>> registrar(@RequestBody Estudiante estudiante) {
        try {
            estudiante.setFechaRegistro(new Date());
            estudiante.setRol("estudiante");
            estudiante.setCurriculum(null); 

            pService.insert(estudiante);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Estudiante registrado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al registrar estudiante: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }


    @GetMapping
    public List<Estudiante> listar() {
        return pService.list();
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable("id") Integer idEstudiante) {
        return pService.listId_Estudiante(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable("id") Integer idEstudiante, @RequestBody Estudiante estudiante) {
        estudiante.setId(idEstudiante);
        pService.update(estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer idEstudiante) {
        pService.delete(idEstudiante);
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
