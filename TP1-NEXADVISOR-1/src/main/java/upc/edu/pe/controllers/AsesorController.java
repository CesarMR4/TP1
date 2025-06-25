package upc.edu.pe.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import upc.edu.pe.entities.Asesor;
import upc.edu.pe.entities.Auxiliar;
import upc.edu.pe.serviceinterface.AsesorService;

@RestController
@RequestMapping("/asesores")
public class AsesorController {

    @Autowired
    private AsesorService aService;

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> registrarAsesor(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") String telefono,
            @RequestParam("sector") String sector,
            @RequestParam("carrera") String carrera,
            @RequestParam("curriculum") MultipartFile file) {

        Map<String, String> response = new HashMap<>();

        try {
            if (file == null || file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
                response.put("error", "El archivo debe ser un PDF y no estar vacío.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Asesor asesor = new Asesor();
            asesor.setNombre(nombre);
            asesor.setEmail(email);
            asesor.setPassword(password);
            asesor.setDireccion(direccion);
            asesor.setTelefono(telefono);
            asesor.setSector(sector);
            asesor.setCarrera(carrera);
            asesor.setCurriculum(file.getBytes());
            asesor.setFechaRegistro(new Date());
            asesor.setRol("asesor");

            aService.insert(asesor);

            response.put("mensaje", "Asesor registrado exitosamente.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Error al registrar el asesor: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Asesor> listar() {
        return aService.list();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer idAsesor) {
        aService.delete(idAsesor);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable("id") Integer idAsesor, @RequestBody Asesor asesor) {
        Asesor existente = aService.listId(idAsesor).orElse(null);
        if (existente != null) {
            existente.setNombre(asesor.getNombre());
            existente.setEmail(asesor.getEmail());
            existente.setDireccion(asesor.getDireccion());
            existente.setTelefono(asesor.getTelefono());
            existente.setCarrera(asesor.getCarrera());
            aService.update(existente);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Asesor> obtenerPorId(@PathVariable("id") Integer id) {
        return aService.listId(id)
                .map(asesor -> new ResponseEntity<>(asesor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/login/asesor")
    public Asesor login(@RequestBody Asesor asesor) {
        return aService.login(asesor.getEmail(), asesor.getPassword())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
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

    @GetMapping("/{id}/curriculum")
    public ResponseEntity<byte[]> descargarCurriculum(@PathVariable("id") Integer id) {
        Optional<Asesor> asesorOpt = aService.listId(id);

        if (asesorOpt.isPresent()) {
            Asesor asesor = asesorOpt.get();
            byte[] archivo = asesor.getCurriculum();

            if (archivo == null || archivo.length == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"curriculum_" + asesor.getNombre() + ".pdf\"")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(archivo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/curriculum")
    public ResponseEntity<String> actualizarCurriculum(
            @PathVariable("id") Integer id,
            @RequestParam("curriculum") MultipartFile file) {
        try {
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El archivo debe ser un PDF válido.");
            }

            Optional<Asesor> asesorOpt = aService.listId(id);
            if (asesorOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Asesor no encontrado.");
            }

            Asesor asesor = asesorOpt.get();
            asesor.setCurriculum(file.getBytes());
            aService.update(asesor);

            return ResponseEntity.ok("Currículum actualizado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el currículum: " + e.getMessage());
        }
    }

    @PutMapping("/test")
    public String testPut() {
        return "PUT OK";
    }
}
