package upc.edu.pe.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upc.edu.pe.entities.Historial;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.serviceinterface.HistorialService;
import upc.edu.pe.serviceinterface.ReservaService;
import upc.edu.pe.repositories.ReservaRepository;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private NotificacionController notificacionController;

    @Autowired
    private HistorialService historialService;

    @Autowired
    private ReservaRepository reservaRepository;

    // REGISTRAR RESERVA
    @PostMapping
    public void registrarReserva(@RequestBody Reserva reserva) {
        reservaService.insert(reserva);

        // Notificar al asesor
        int idAsesor = reserva.getAsesor().getId();
        String mensaje = "Nuevo estudiante ha realizado una reserva.";
        notificacionController.notificarReserva(idAsesor, mensaje);

        // Registrar en historial
        Historial historial = new Historial();
        historial.setDescripcion("Reserva con el asesor " + reserva.getAsesor().getNombre());
        historial.setFecha(new Date());
        historial.setEstudiante(reserva.getEstudiante());
        historial.setAsesor(reserva.getAsesor());

        historialService.insert(historial);
    }

    // LISTAR TODAS LAS RESERVAS
    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.list();
    }

    // ELIMINAR RESERVA
    @DeleteMapping
    public void eliminarReserva(@RequestParam int id) {
        reservaService.delete(id);
    }

    // ACTUALIZAR ESTADO
    @PostMapping("/actualizarEstado")
    public void actualizarEstado(@RequestParam int idReserva, @RequestParam String estado) {
        reservaService.updateEstado(idReserva, estado);
    }

    // ACTUALIZAR COMENTARIO
    @PutMapping("/comentario/{id}")
    public void actualizarComentario(@PathVariable("id") Integer idReserva, @RequestBody String comentario) {
        reservaService.actualizarComentario(idReserva, comentario);
    }

    // LISTAR RESERVAS POR ESTUDIANTE
    @GetMapping("/estudiante/{id}")
    public List<Reserva> listarPorEstudiante(@PathVariable("id") int idEstudiante) {
        return reservaService.listarPorEstudiante(idEstudiante);
    }

    // LISTAR RESERVAS POR ASESOR
    @GetMapping("/asesor/{id}")
    public List<Reserva> listarPorAsesor(@PathVariable("id") int idAsesor) {
        return reservaService.listarPorAsesor(idAsesor);
    }

    // ACTUALIZAR PUNTUACIÓN DE RESERVA
    @PutMapping("/{id}/puntuacion")
    public ResponseEntity<Reserva> actualizarPuntuacion(@PathVariable("id") Integer idReserva, @RequestBody Integer puntuacion) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);

        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reserva.setPuntuacion(puntuacion);	
            reservaRepository.save(reserva);
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
