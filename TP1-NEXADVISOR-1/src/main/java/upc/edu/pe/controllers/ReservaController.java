package upc.edu.pe.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upc.edu.pe.dto.ReservaDTO;
import upc.edu.pe.entities.Asesor;
import upc.edu.pe.entities.Estudiante;
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

    // ✅ REGISTRAR RESERVA (CORREGIDO)
    @PostMapping
    public void registrarReserva(@RequestBody Reserva reserva) {
        // Asegura que solo se utilicen los IDs para evitar duplicación
        int idAsesor = reserva.getAsesor().getId();
        Asesor asesor = new Asesor();
        asesor.setId(idAsesor);
        reserva.setAsesor(asesor);

        int idEstudiante = reserva.getEstudiante().getId();
        Estudiante estudiante = new Estudiante();
        estudiante.setId(idEstudiante);
        reserva.setEstudiante(estudiante);

        reservaService.insert(reserva);

        // Notificar al asesor
        String mensaje = "Nuevo estudiante ha realizado una reserva.";
        notificacionController.notificarReserva(idAsesor, mensaje);

        // Registrar en historial
        Historial historial = new Historial();
        historial.setDescripcion("Reserva con el asesor " + idAsesor);
        historial.setFecha(new Date());
        historial.setEstudiante(estudiante);
        historial.setAsesor(asesor);

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
    public List<ReservaDTO> listarPorAsesor(@PathVariable("id") int idAsesor) {
        List<Reserva> reservas = reservaService.listarPorAsesor(idAsesor);

        List<ReservaDTO> dtos = reservas.stream().map(reserva -> {
            Estudiante estudiante = reserva.getEstudiante();
            return new ReservaDTO(
                reserva.getId(),
                reserva.getEstado(),
                reserva.getComentarioAsesor(),
                reserva.getHoraReserva(),
                reserva.getFechaReserva(),
                estudiante != null ? estudiante.getId() : 0,
                estudiante != null ? estudiante.getNombre() : "Desconocido"
            );
        }).toList();

        return dtos;
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
